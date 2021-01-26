package tech.dtech.athena.document.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import tech.dtech.athena.config.validation.exceptions.ResourceNotFoundException;
import tech.dtech.athena.config.validation.exceptions.dto.ErrorDTO;
import tech.dtech.athena.customer.DuplicatedRecordException;
import tech.dtech.athena.document.model.DocumentType;
import tech.dtech.athena.document.model.DocumentTypeDTO;
import tech.dtech.athena.login.model.Account;
import tech.dtech.athena.login.repository.AccountRepository;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final HttpHeaders headers = new HttpHeaders();
    private static int idCounter = 0;

    @BeforeAll
    static void setUp(@Autowired AccountRepository accountRepository, @Autowired MockMvc mockMvc) throws Exception {
        Account account = new Account(
                "Goku",
                "goku@kamehouse.com",
                "$2a$10$AzmiQREFLZUnxQKj1ZM.mO1uso0WVOsRcP7kV.MqEVKhZ/bV6vQfu");

        accountRepository.save(account);

        String json = "{\"email\":\"goku@kamehouse.com\", \"password\":\"12345678\"}";

        MvcResult loginResult = mockMvc.perform(MockMvcRequestBuilders
                .post(new URI("/login"))
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        String stringResult = loginResult.getResponse().getContentAsString();
        JsonNode loginKeys = objectMapper.readTree(stringResult);
        headers.add("Authorization", ("Bearer " + loginKeys.get("token")).replace("\"", ""));
    }

    @AfterAll
    static void tearDown(@Autowired AccountRepository accountRepository) {
        accountRepository.deleteAll();
    }

    @Transactional
    @Test
    public void shouldCreateWith201_thenTryToCreateDuplicatedAndGet422_thenGetAllThatSucceededWith200() throws Exception {
        String uriString = "/documents/types";
        URI uri = new URI(uriString);

        DocumentType documentType = new DocumentType();
        documentType.setId(++idCounter);
        documentType.setName("CPF");

        String locationUri = "http://localhost" + uriString + "/" + documentType.getId();

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(objectMapper.writeValueAsString(documentType))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(MockMvcResultMatchers.header().stringValues(HttpHeaders.LOCATION, locationUri))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(new DocumentTypeDTO(documentType))));

        DocumentType documentType2 = new DocumentType();
        documentType2.setId(++idCounter);
        documentType2.setName("RG");

        String locationUri2 = "http://localhost" + uriString + "/" + documentType2.getId();

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(objectMapper.writeValueAsString(documentType2))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(MockMvcResultMatchers.header().stringValues(HttpHeaders.LOCATION, locationUri2))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(new DocumentTypeDTO(documentType2))));

        DocumentType documentType3 = new DocumentType();
        documentType3.setId(0L);
        documentType3.setName("CPF");

        Exception expectedException = new DuplicatedRecordException(DocumentType.ENTITY_NAME, DocumentType.FIELD_NAME_NAME);
        ErrorDTO expectedError = new ErrorDTO(expectedException.getMessage());

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(objectMapper.writeValueAsString(documentType3))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedError)));

        List<DocumentTypeDTO> expectedFinalResponse = Arrays.asList(
                new DocumentTypeDTO(documentType),
                new DocumentTypeDTO(documentType2));

        mockMvc.perform(MockMvcRequestBuilders.get(uri).headers(headers))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedFinalResponse)));
    }

    @Transactional
    @Test
    public void shouldReturnEmptyResponse_whenThereAreNoTypes() throws Exception {
        URI uri = new URI("/documents/types");

        mockMvc.perform(MockMvcRequestBuilders.get(uri).headers(headers))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(Collections.emptyList())));
    }

    @Transactional
    @Test
    public void shouldCreateNewRecordWith201_ThenUpdateItWith200() throws Exception {
        String uriString = "/documents/types";
        URI uri = new URI(uriString);

        DocumentType documentType = new DocumentType();
        documentType.setId(++idCounter);
        documentType.setName("CPF");

        String locationUri = "http://localhost" + uriString + "/" + documentType.getId();

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(objectMapper.writeValueAsString(documentType))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(MockMvcResultMatchers.header().stringValues(HttpHeaders.LOCATION, locationUri))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(new DocumentTypeDTO(documentType))));

        DocumentType updatedDocumentType = new DocumentType();
        updatedDocumentType.setId(idCounter);
        updatedDocumentType.setName("RG");

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/" + documentType.getId())
                .content(objectMapper.writeValueAsString(updatedDocumentType))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(new DocumentTypeDTO(updatedDocumentType))));

        List<DocumentTypeDTO> expectedFinalResponse = Collections.singletonList(
                new DocumentTypeDTO(updatedDocumentType));

        mockMvc.perform(MockMvcRequestBuilders.get(uri).headers(headers))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedFinalResponse)));
    }

    @Transactional
    @Test
    public void shouldTryToUpdateRecord_ThenFailWithNoSuchElementException_AndReturn404() throws Exception {
        String uriString = "/documents/types";
        URI uri = new URI(uriString);

        DocumentType updatedDocumentType = new DocumentType();
        updatedDocumentType.setId(0L);
        updatedDocumentType.setName("RG");

        Exception exception = new ResourceNotFoundException(DocumentType.ENTITY_NAME);
        String errorMessage = exception.getMessage();

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/" + updatedDocumentType.getId())
                .content(objectMapper.writeValueAsString(updatedDocumentType))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(new ErrorDTO(errorMessage))));
    }
}
