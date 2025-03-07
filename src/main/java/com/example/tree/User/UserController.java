package com.example.tree.User;

import com.example.tree.User.Dto.UserLoginRequest;
import com.example.tree.User.Dto.UserLoginResponse;
import com.example.tree.User.Dto.UserUpdate;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import java.util.Optional;

@RestController
public class UserController {

    public final UserService userService;
    public final LoginUserResolver loginUserResolver;

    public UserController(UserService userService, LoginUserResolver loginUserResolver) {
        this.userService = userService;
        this.loginUserResolver = loginUserResolver;
    }

    //회원가입(POST/users/signup)RequestBody/valid
    @PostMapping("/users/signup")
    public void create(@Valid @RequestBody UserRequest request) {
        userService.save(request);
    }

    //로그인(POST/users/login)RequestBody/valid
    @PostMapping("/users/login")
    public UserLoginResponse loginUser(@Valid @RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    //회원 수정(PUT/users)(토큰)/RequestBody

    @PutMapping("/users")
    public void update(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @Valid @RequestBody UserUpdate request) {
        User user = loginUserResolver.resolveUserFromToken(token);
        userService.update(user, request);



    }
    //회원 삭제(Delete/users)(토큰)/RequestBody

    @DeleteMapping("/users")
    public void delete(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token

    ){
        User user = loginUserResolver.resolveUserFromToken(token);
        userService.delete(user);
    }
}

