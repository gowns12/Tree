package com.example.tree.User;

import com.example.tree.User.Dto.JwtProvider;
import org.springframework.stereotype.Component;

@Component
public class LoginUserResolver {
    public final UserService userService;
    public final UserRepository userRepository;
    public final JwtProvider jwtProvider;

    private static final String BEARER_PREFIX = "Bearer ";
    public static final String INVALID_TOKEN_MESSAGE = "로그인 정보가 유효하지 않습니다";


    public LoginUserResolver(UserService userService, UserRepository userRepository, JwtProvider jwtProvider) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public User resolveUserFromToken(String bearerToken){
        String token = extractToken(bearerToken);
        if (!jwtProvider.isValidToken(token)) {
            throw new IllegalArgumentException(INVALID_TOKEN_MESSAGE);
        }
        String loginId = jwtProvider.getSubject(token);
        return userRepository.findByLoginId(loginId);
    }

    private String extractToken(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith(BEARER_PREFIX)) {
            throw new IllegalArgumentException(INVALID_TOKEN_MESSAGE);
        }
        return bearerToken.substring(BEARER_PREFIX.length());
    }

    }



