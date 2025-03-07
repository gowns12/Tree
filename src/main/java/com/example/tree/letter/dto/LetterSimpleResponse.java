package com.example.tree.letter.dto;

import com.example.tree.letter.domain.Letter;

public record LetterSimpleResponse(
        String nickname
) {
    public static LetterSimpleResponse toDto(Letter letter){
        return new LetterSimpleResponse(letter.getNickname());
    }
}
