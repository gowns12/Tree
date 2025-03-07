package com.example.tree.User.Dto;

public record UserUpdate(
        String loginId,
        String password,
        String nickName
) {
}
