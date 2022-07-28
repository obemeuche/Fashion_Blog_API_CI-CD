package com.example.fashionblog.dto;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupDto {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
