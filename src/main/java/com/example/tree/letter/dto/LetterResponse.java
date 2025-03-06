package com.example.tree.letter.dto;

import com.example.tree.letter.domain.Letter;
import com.example.tree.letter.domain.Recommend;

import java.time.LocalDateTime;

public record LetterResponse(
        Long id,
        String content,
        String nickname,
        LocalDateTime createdTime,
        Integer recommendCount
) {
    public static LetterResponse toDto(Letter letter){
        return new LetterResponse(
                letter.getId(),
                letter.getContent(),
                letter.getNickname(),
                letter.getCreatedTime(),
                letter.getRecommends().size()
                );
    }
}
