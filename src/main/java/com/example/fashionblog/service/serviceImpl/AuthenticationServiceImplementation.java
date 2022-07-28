package com.example.fashionblog.service.serviceImpl;

import com.example.fashionblog.dto.LoginDto;
import com.example.fashionblog.dto.SignupDto;
import com.example.fashionblog.enums.Role;
import com.example.fashionblog.exception.UserAlreadyExist;
import com.example.fashionblog.exception.UserNotFound;
import com.example.fashionblog.model.User;
import com.example.fashionblog.repository.AuthenticationRepository;
import com.example.fashionblog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    private final HttpSession httpSession;

    @Override
    public String signup(SignupDto signupDto) {
        String email = signupDto.getEmail();
        boolean user = authenticationRepository.existsByEmail(email);

        if (user) {
            throw new UserAlreadyExist("User with " + email + " already exists");
        }

        User user1 = User.builder()
                .firstName(signupDto.getFirstName())
                .lastname(signupDto.getLastName())
                .email(signupDto.getEmail())
                .password(signupDto.getPassword())
                .role(Role.CUSTOMER)
                .comment(new ArrayList<>())
                .post(new ArrayList<>())
                .likedPost(new ArrayList<>())
                .build();

        authenticationRepository.save(user1);

        return "Welcome to the family " + user1.getFirstName() + user1.getLastname();
    }

    @Override
    public String login(LoginDto loginDto) {
        String userEmail = loginDto.getEmail();

        User user = authenticationRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFound("User email or password not found!"));

        if (!loginDto.getPassword().equals(user.getPassword())) {
            throw new UserNotFound("User email or password is incorrect!");
        }

        httpSession.setAttribute("user_id", user.getId());

        return "Login successful";

    }

    @Override
    public String logout() {
        httpSession.invalidate();
        return "Logout successful!";
    }

}
