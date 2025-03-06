package com.example.tree.letter.domain;

import com.example.tree.User.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import java.util.Objects;

@Entity
public class Recommend {
    //복합키를 이용해 추천 중복방지
    //복합키는 Letter와 User를 키로 가짐
    @EmbeddedId
    private RecommendId id;
    @ManyToOne
    @MapsId("letterId")
    private Letter letter;
    @ManyToOne
    @MapsId("userId")
    private User user;

    public Recommend() {
    }

    public RecommendId getId() {
        return id;
    }

    public Recommend(Letter letter, User user) {
        this.letter = letter;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recommend recommend = (Recommend) o;
        return Objects.equals(id, recommend.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "id=" + id +
                '}';
    }
}
