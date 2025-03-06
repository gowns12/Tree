package com.example.tree.tree;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

//    @ManyToOne
//    private User user;
//    @OneToMany
//    private <List>Letter letter;


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

}
