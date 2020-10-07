package tech.dtech.athena.login.controller;

import java.net.URI;

import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String emptyEmailError = "{\"field\":\"email\",\"message\":\"must not be empty\"}";
    private String nullEmailError = "{\"field\":\"email\",\"message\":\"must not be null\"}";
    private String malformedEmailError = "{\"field\":\"email\",\"message\":\"must be a well-formed email address\"}";
    private String emptyPasswordError = "{\"field\":\"password\",\"message\":\"must not be empty\"}";
    private String invalidLengthPasswordError = "{\"field\":\"password\",\"message\":\"length must be between 8 and 2147483647\"}";
    private String nullPasswordError = "{\"field\":\"password\",\"message\":\"must not be null\"}";

    @Test
    public void shouldReturnToken_whenCredentialsAreCorrect() throws Exception {
        URI uri = new URI("/login");
        String json = "{\"email\":\"goku@kamehouse.com\", \"password\":\"12345678\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string(new StringContains("\"type\":\"Bearer\"")))
                .andExpect(MockMvcResultMatchers.content().string(new StringContains("\"token\":")));
    }

    @Test
    public void shouldReturnUnauthorized_whenBothCredentialsAreWrong() throws Exception {
        URI uri = new URI("/login");
        String json = "{\"email\":\"vegeta@kamehouse.com\", \"password\":\"12345678\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    public void shouldReturnUnauthorized_whenPasswordIsWrong() throws Exception {
        URI uri = new URI("/login");
        String json = "{\"email\":\"goku@kamehouse.com\", \"password\":\"123456789\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    public void shouldReturnUnauthorized_whenPasswordExistsButUsernameDont() throws Exception {
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
                .andExpect(MockMvcResultMatchers.content().json("[" + emptyEmailError + "," + nullEmailError + "]"));
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
                .andExpect(MockMvcResultMatchers.content().string("[" + malformedEmailError + "]"));
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
                .andExpect(MockMvcResultMatchers.content().json("[" + emptyPasswordError + "," + nullPasswordError + "]"));
    }

    @Test
    public void shouldReturnLenthError_whenPasswordIsShorterThan8Characters() throws Exception {
        URI uri = new URI("/login");
        String json = "{\"email\":\"goku@kamehouse.com\", \"password\":\"1234567\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.content().string("[" + invalidLengthPasswordError + "]"));
    }

}