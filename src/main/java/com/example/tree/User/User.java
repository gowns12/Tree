package com.example.tree.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Id를 입력해주세요.")
    String userId;
    @NotBlank(message = "Password  입력해주세요.")
    String password;
    @NotBlank(message = "닉네임을 입력해주세요.")
    String nickname;
}
