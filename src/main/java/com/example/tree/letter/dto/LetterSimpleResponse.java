package com.example.tree.letter.dto;

import com.example.tree.letter.domain.Letter;

public record LetterSimpleResponse(
        Long id,
        String nickname
) {
    public static LetterSimpleResponse toDto(Letter letter){
        return new LetterSimpleResponse(letter.getId(), letter.getNickname());
    }
    public static LetterSimpleResponse toDto(QueryLetterDto letterDto){
        return new LetterSimpleResponse(letterDto.id(), letterDto.nickname());
    }
}
