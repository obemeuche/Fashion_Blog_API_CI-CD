package com.example.fashionblog.controller;

import com.example.fashionblog.dto.LoginDto;
import com.example.fashionblog.dto.SignupDto;
import com.example.fashionblog.enums.Role;
import com.example.fashionblog.model.User;
import com.example.fashionblog.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService service;

    @Autowired
    private HttpSession httpSession;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .firstName("Uchechi")
                .lastname("Obeme")
                .email("obemeuchechi@gmail.com")
                .password("1234")
                .id(1L)
                .build();
    }

    @Test
    void signup() throws Exception {
        SignupDto signupDto =
                SignupDto.builder()
                        .firstName("Peter")
                        .lastName("Okoye")
                        .email("peter@gmail.com")
                        .password("1234")
                        .build();

        Mockito.when(service.signup(signupDto))
                .thenReturn(String.valueOf(user));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\":\"Uchechi\",\n" +
                        "    \"lastName\":\"Obeme\",\n" +
                        "    \"email\":\"obemeuchechi@gmail.com\",\n" +
                        "    \"password\":\"1234\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    @DisplayName("Login controller test")
    void login() throws Exception {
        LoginDto loginDto =
                LoginDto.builder()
                .email("peter@gmail.com")
                .password("1234")
                .build();

        Mockito.when(service.login(loginDto))
                .thenReturn(String.valueOf(user));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\":\"obemeuchechi@gmail.com\",\n" +
                                "    \"password\":\"1234\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void logout() throws Exception {
        String msg = "Logout successful";

        Mockito.when(service.logout()).thenReturn(msg);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/logout"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}