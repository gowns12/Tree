package com.example.tree.tree;

import com.example.tree.BaseTimeEntity;
import com.example.tree.User.User;
import com.example.tree.letter.domain.Letter;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Tree extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String urlPath;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "tree")
    private List<Letter> letterList;

    private boolean isOpen;


    public Tree(String title, User user) {
        this.title = title;
        this.user = user;
        this.isOpen = true;
        this.urlPath= UUID.randomUUID().toString();
    }

    public Tree(String title) {
        this.title = title;
    }

    protected Tree() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public User getUser() {
        return user;
    }

    public List<Letter> getLetterList() {
        return letterList;
    }

    public void isClose(){
        this.isOpen=!isOpen;
    }
}
