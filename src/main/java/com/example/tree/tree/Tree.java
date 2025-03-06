package com.example.tree.tree;

import com.example.tree.BaseTimeEntity;
import com.example.tree.User.User;
import com.example.tree.letter.domain.Letter;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Tree extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "tree")
    private List<Letter> letterList;


    public Tree(User user, String title) {
        this.user = user;
        this.title = title;
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

    public User getUser() {
        return user;
    }

    public List<Letter> getLetterList() {
        return letterList;
    }
}
