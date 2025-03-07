package com.example.tree.letter.domain;

import com.example.tree.User.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

//Recommend 의 복합키
@Embeddable
public class RecommendId implements Serializable {

    private Long userId;
    private Long letterId;

    public Long getUserId() {
        return userId;
    }

    public Long getLetterId() {
        return letterId;
    }

    public RecommendId() {
    }

    public RecommendId(Long userId, Long letterId) {
        this.userId = userId;
        this.letterId = letterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendId that = (RecommendId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(letterId, that.letterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, letterId);
    }

    @Override
    public String toString() {
        return "RecommendId{" +
                "userId=" + userId +
                ", letterId=" + letterId +
                '}';
    }
}
