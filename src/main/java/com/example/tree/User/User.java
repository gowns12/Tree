package com.example.tree.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
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
