package com.example.tree.User;


import com.example.tree.User.Dto.JwtProvider;
import com.example.tree.User.Dto.UserLoginRequest;
import com.example.tree.User.Dto.UserLoginResponse;
import com.example.tree.User.Dto.UserUpdate;
import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public final UserRepository userRepository;
    public final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }
    //회원가입
    public void save(UserRequest request) {
        String password = SecurityUtils.sha256EncryptHex2(request.password());
        userRepository.save(new User(
                request.loginId(),
                password,
                request.nickName()
        ));

    }
    //로그인
    public UserLoginResponse login(UserLoginRequest request) {
        String encryptedPassword = SecurityUtils.sha256EncryptHex2(request.password()); // 암호화된 패스워드

        // loginId로 사용자 찾기
        User user = userRepository.findByLoginId(request.loginId());

        // 사용자 존재 확인
        if (user == null) {
            throw new RuntimeException("Customer not found");
        }

        // 비밀번호 확인
        if (!user.getPassword().equals(encryptedPassword)) {
            throw new RuntimeException("Invalid password");
        }


        // 로그인 성공, 유저 정보를 담은 응답 객체 반환
        return new UserLoginResponse(user.getUserId(),jwtProvider.createToken(request.loginId()));
    }

    //수정
    @Transactional
    public void update(User user, UserUpdate request) {
        user.updateWith(
                request.nickName()
        );
    }
    //삭제
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }
}
