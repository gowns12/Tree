package com.example.tree.letter.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Recommend {
    @EmbeddedId
    private RecommendId id;

    public Recommend() {
    }

    public RecommendId getId() {
        return id;
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
