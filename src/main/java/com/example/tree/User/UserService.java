package com.example.tree.User;

import com.example.tree.User.Dto.UserLoginResponse;
import jakarta.validation.Valid;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //회원가입
    public void save(@Valid UserRequest request) {
        String password = SecurityUtils.sha256EncryptHex2(request.password());
        userRepository.save(new User(
                request.loginId(),
                password,
                request.nickname()
        ));

    }
    //로그인
    public UserLoginResponse login(@Valid UserRequest request) {
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
        return new UserLoginResponse(user.getUserId());
    }

    //수정
    public UserLoginResponse update(@Valid UserRequest request) {
        return null;
    }
}
