package com.example.tree.letter.dto;

import com.example.tree.User.User;
import com.example.tree.letter.domain.Letter;
import com.example.tree.tree.Tree;
import jakarta.validation.constraints.Size;

public record LetterRequest(
        @Size(max = 300,min = 1)
        String content,
        String nickname,
        Long treeId,
        Long userId
) {
        public Letter toEntity(Tree tree, User user){
                return new Letter(
                        content,
                        nickname,
                        tree,
                        user
                );
        }
}
