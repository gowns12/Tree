package com.example.tree.User;

public record UserRequest(
        String loginId,
        String password,
        String nickname
) {
}
