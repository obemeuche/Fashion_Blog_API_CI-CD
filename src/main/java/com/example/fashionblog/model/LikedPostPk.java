package com.example.fashionblog.model;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LikedPostPk implements Serializable {
    private Post post;
    private User user;
}
