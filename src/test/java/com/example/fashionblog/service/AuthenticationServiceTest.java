package com.example.fashionblog.service;

import com.example.fashionblog.dto.LoginDto;
import com.example.fashionblog.dto.SignupDto;
import com.example.fashionblog.enums.Role;
import com.example.fashionblog.exception.UserAlreadyExist;
import com.example.fashionblog.model.User;
import com.example.fashionblog.repository.AuthenticationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService service;

    @MockBean
    private AuthenticationRepository repository;

    @Autowired
    private HttpSession httpSession;

    @BeforeEach
    void setUp(){
        SignupDto signupDto = new SignupDto();
        String email = signupDto.getEmail();
        boolean getUser = repository.existsByEmail(email);

        if (getUser) {
            throw new UserAlreadyExist("User with " + email + " already exists");
        }

        User user = User.builder()
                .firstName("Emmanuela")
                .lastname("Ike")
                .email("ella@gmail.com")
                .password("12345")
                .role(Role.CUSTOMER)
                .build();

        Mockito.when(repository.findByEmail("ella@gmail.com")).thenReturn(Optional.ofNullable(user));
    }

    @Test
    @DisplayName("Registering a new user")
    public void signup() {
        SignupDto signupDto = SignupDto.builder()
                .email("ella@gmail.com")
                .password("12345")
                .build();

        String msg = "Welcome to the family " + signupDto.getFirstName() + signupDto.getLastName();

        assertEquals(msg, service.signup(signupDto));
    }


    @Test
    @DisplayName("User login")
    void login() {
        LoginDto loginDto =
                LoginDto.builder()
                        .email("ella@gmail.com")
                        .password("12345")
                        .build();

        String msg = "Login successful";

        assertEquals(msg, service.login(loginDto));
    }

    @Test
    void logout() {
        String msg = "Logout successful!";
        assertEquals(msg, service.logout());
    }
}