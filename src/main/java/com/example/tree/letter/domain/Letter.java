package com.example.tree.letter.domain;

import com.example.tree.BaseTimeEntity;
import com.example.tree.User.User;
import com.example.tree.tree.Tree;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Letter extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    private String nickname;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tree tree;

    private Boolean isDeleted = false;

    private Boolean isInvisible = true;


    public User getUser() {
        return user;
    }

    public Tree getTree() {
        return tree;
    }

    public Letter() {
    }

    public Letter(String content, String nickName, Tree tree, User user) {
        this.content = content;
        this.nickname = nickName;
        this.tree = tree;
//        tree.getLetter().add(this);
        this.user = user;
//        user.getLetters().add(this);
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getNickname() {
        return nickname;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Boolean getInvisible() {
        return isInvisible;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter = (Letter) o;
        return Objects.equals(id, letter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", nickName='" + nickname + '\'' +
                ", isDeleted=" + isDeleted +
                ", isInvisible=" + isInvisible +
                '}';
    }

    public void update(@Size(max = 300,min = 1) String content, String nickname) {
        this.content = content;
        this.nickname = nickname;
    }

    public void delete() {
        isDeleted = true;
    }
}
