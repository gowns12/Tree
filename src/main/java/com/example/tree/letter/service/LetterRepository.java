package com.example.tree.letter.service;

import com.example.tree.letter.domain.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterRepository extends JpaRepository<Letter,Long> {
    List<Letter> findAllByTreeId(Long treeId);
}
