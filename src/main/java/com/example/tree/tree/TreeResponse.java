package com.example.tree.tree;

import com.example.tree.letter.domain.Letter;

import java.util.List;

public record TreeResponse(
        Long id,
        String title,
        Long userId,
        List<Letter> letterList
) {
}
