package com.example.tree.tree;

import com.example.tree.letter.domain.Letter;
import com.example.tree.letter.dto.LetterSimpleResponse;

import java.util.List;

public record TreeResponse(
        Long id,
        String title,
        String urlPath,
        Boolean isOpen,
        Long userId,
        List<LetterSimpleResponse> letterList
) {
}
