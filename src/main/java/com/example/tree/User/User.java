package com.example.tree.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users") //테이블 이름이 곂쳐서 새 테이블 이름 지정해줌
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Id를 입력해주세요.")
    private String userId;

    @NotBlank(message = "Password  입력해주세요.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;

    public User() {
    }

    public User(String userId, String password, String nickname) {

        this.userId = userId;
        this.password = password;
        this.nickName = nickname;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickName;
    }

    public void updateWith(String nickName) {

        this.nickName = nickName;

    }

}
