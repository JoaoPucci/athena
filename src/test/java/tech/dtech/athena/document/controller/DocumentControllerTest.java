package tech.dtech.athena.document.controller;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tech.dtech.athena.document.model.DocumentType;
import tech.dtech.athena.document.repository.DocumentTypeRepository;
import tech.dtech.athena.login.model.Account;
import tech.dtech.athena.login.repository.AccountRepository;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DocumentControllerTest {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private DocumentTypeRepository repository;

    @Autowired
    private MockMvc mockMvc;
    
    private HttpHeaders headers = new HttpHeaders();
    
    @BeforeEach
    public void setUp() throws Exception {
        Account account = new Account("Goku", "goku@kamehouse.com", "$2a$10$AzmiQREFLZUnxQKj1ZM.mO1uso0WVOsRcP7kV.MqEVKhZ/bV6vQfu");
        accountRepository.save(account);

        URI loginUri = new URI("/login");
        String json = "{\"email\":\"goku@kamehouse.com\", \"password\":\"12345678\"}";
        MvcResult loginResult = mockMvc.perform(MockMvcRequestBuilders.post(loginUri).content(json).contentType(MediaType.APPLICATION_JSON)).andReturn();
        String stringResult = loginResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode loginKeys = mapper.readTree(stringResult);

        headers.add("Authorization",("Bearer " + loginKeys.get("token")).replace("\"", ""));
    }
    
    @Transactional
    @Test
    public void shouldReturnDocumentTypes_whenThereAreTypes() throws Exception {
        URI uri = new URI("/documents/types");
        
        DocumentType documentType = new DocumentType();

        documentType.setId(1L);
        documentType.setName("CPF");
        repository.save(documentType);

        documentType.setId(2L);
        documentType.setName("RG");
        repository.save(documentType);

        String filledResponse = ""
                + "["
                + "    {"
                + "        \"id\": 1,"
                + "        \"name\": \"CPF\""
                + "    },"
                + "    {"
                + "        \"id\": 2,"
                + "        \"name\": \"RG\""
                + "    }"
                + "]";

        mockMvc.perform(MockMvcRequestBuilders.get(uri).headers(headers))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().json(filledResponse));
    }

    @Transactional
    @Test
    public void shouldReturnEmptyResponse_whenThereAreNoTypes() throws Exception {
        URI uri = new URI("/documents/types");

        String emptyResponse = "[]";

        mockMvc.perform(MockMvcRequestBuilders.get(uri).headers(headers))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().json(emptyResponse));
    }
}
