package tech.dtech.athena.login.controller;

import java.net.URI;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import tech.dtech.athena.login.model.Account;
import tech.dtech.athena.repository.AccountRepository;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LoginControllerTest {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private String emptyEmailError = "{\"field\":\"email\",\"message\":\"must not be empty\"}";
    private String malformedEmailError = "{\"field\":\"email\",\"message\":\"must be a well-formed email address\"}";
    private String emptyPasswordError = "{\"field\":\"password\",\"message\":\"must not be empty\"}";
    private String invalidLengthPasswordError = "{\"field\":\"password\",\"message\":\"length must be between 8 and 255\"}";

    @Transactional
    @Test
    public void shouldReturnToken_whenCredentialsAreCorrect() throws Exception {
        Account account = new Account("Goku", "goku@kamehouse.com", "$2a$10$AzmiQREFLZUnxQKj1ZM.mO1uso0WVOsRcP7kV.MqEVKhZ/bV6vQfu");
        repository.save(account);

        URI uri = new URI("/login");
        String json = "{\"email\":\"goku@kamehouse.com\", \"password\":\"12345678\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string(new StringContains("\"type\":\"Bearer\"")))
                .andExpect(MockMvcResultMatchers.content().string(new StringContains("\"token\":")));
    }

    @Transactional
    @Test
    public void shouldReturnUnauthorized_whenBothCredentialsAreWrong() throws Exception {
        Account account = new Account("Goku", "goku@kamehouse.com", "$2a$10$AzmiQREFLZUnxQKj1ZM.mO1uso0WVOsRcP7kV.MqEVKhZ/bV6vQfu");
        repository.save(account);

        URI uri = new URI("/login");
        String json = "{\"email\":\"vegeta@kamehouse.com\", \"password\":\"12345678\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Transactional
    @Test
    public void shouldReturnUnauthorized_whenPasswordIsWrong() throws Exception {
        Account account = new Account("Goku", "goku@kamehouse.com", "$2a$10$AzmiQREFLZUnxQKj1ZM.mO1uso0WVOsRcP7kV.MqEVKhZ/bV6vQfu");
        repository.save(account);

        URI uri = new URI("/login");
        String json = "{\"email\":\"goku@kamehouse.com\", \"password\":\"123456789\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Transactional
    @Test
    public void shouldReturnUnauthorized_whenPasswordExistsButUsernameDont() throws Exception {
        Account account = new Account("Goku", "goku@kamehouse.com", "$2a$10$AzmiQREFLZUnxQKj1ZM.mO1uso0WVOsRcP7kV.MqEVKhZ/bV6vQfu");
        repository.save(account);

        URI uri = new URI("/login");
        String json = "{\"email\":\"gintaman@kamehouse.com\", \"password\":\"12345678\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    public void shouldReturnNullAndEmptyErrors_whenEmailIsNull() throws Exception {
        URI uri = new URI("/login");
        String json = "{\"email\":null, \"password\":\"12345678\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.content().json("[" + emptyEmailError + "]"));
    }

    @Test
    public void shouldReturnEmptyErrors_whenEmailIsEmpty() throws Exception {
        URI uri = new URI("/login");
        String json = "{\"email\":\"\", \"password\":\"12345678\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.content().json("[" + emptyEmailError + "]"));
    }

    @Test
    public void shouldReturnMalformedError_whenEmailIsMalformed() throws Exception {
        URI uri = new URI("/login");
        String json = "{\"email\":\"notanemail.com\", \"password\":\"12345678\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.content().json("[" + malformedEmailError + "]"));
    }

    @Test
    public void shouldReturnEmptyAndLengthError_whenPasswordIsEmpty() throws Exception {
        URI uri = new URI("/login");
        String json = "{\"email\":\"goku@kamehouse.com\", \"password\":\"\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.content().json("[" + emptyPasswordError + "," + invalidLengthPasswordError + "]"));
    }

    @Test
    public void shouldReturnEmptyAndNullError_whenPasswordIsNull() throws Exception {
        URI uri = new URI("/login");
        String json = "{\"email\":\"goku@kamehouse.com\", \"password\":null}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.content().json("[" + emptyPasswordError + "]"));
    }

    @Test
    public void shouldReturnLenthError_whenPasswordIsShorterThan8Characters() throws Exception {
        URI uri = new URI("/login");
        String json = "{\"email\":\"goku@kamehouse.com\", \"password\":\"1234567\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.content().json("[" + invalidLengthPasswordError + "]"));
    }

}
