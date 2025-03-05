package com.example.tree.letter.dto;

import jakarta.validation.constraints.Size;

public record LetterRequest(
        @Size(max = 300,min = 1)
        String content,
        String nickname
) {
}
