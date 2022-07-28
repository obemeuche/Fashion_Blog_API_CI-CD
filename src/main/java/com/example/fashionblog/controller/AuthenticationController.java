package com.example.fashionblog.controller;

import com.example.fashionblog.dto.LoginDto;
import com.example.fashionblog.dto.SignupDto;
import com.example.fashionblog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signUp")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto){
        return ResponseEntity.ok(authenticationService.signup(signupDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authenticationService.login(loginDto));
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok(authenticationService.logout());
    }
}
