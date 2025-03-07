package com.example.tree.User;

import com.example.tree.letter.domain.Letter;
import com.example.tree.tree.Tree;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users") //테이블 이름이 곂쳐서 새 테이블 이름 지정해줌
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Id를 입력해주세요.")
    private String loginId;

    @NotBlank(message = "Password  입력해주세요.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;

    @OneToMany(mappedBy = "user")
    private List<Tree> treeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Letter> letters = new ArrayList<>();

    public User() {
    }

    public User(String userId, String password, String nickname) {

        this.loginId = userId;
        this.password = password;
        this.nickName = nickname;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickName;
    }

    public List<Tree> getTreeList() {
        return treeList;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void updateWith(String loginId, String password, String nickName) {

        this.loginId = loginId;
        this.password = password;
        this.nickName = nickName;


    }

}
