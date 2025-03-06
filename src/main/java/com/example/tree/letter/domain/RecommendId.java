package com.example.tree.letter.domain;

import com.example.tree.User.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecommendId implements Serializable {
    @ManyToOne
    private Letter letter;
    @ManyToOne
    private User user;

    public RecommendId(Letter letter, User user) {
        this.letter = letter;
        this.user = user;
    }

    public RecommendId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendId that = (RecommendId) o;
        return Objects.equals(letter, that.letter) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, user);
    }

    @Override
    public String toString() {
        return "RecommendId{" +
                "letter=" + letter +
                ", user=" + user +
                '}';
    }
}
