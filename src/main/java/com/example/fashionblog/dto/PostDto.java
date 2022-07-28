package com.example.fashionblog.dto;

import lombok.*;

import javax.persistence.Column;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private String title;

    private String imgUrl;

    private String content;
}
