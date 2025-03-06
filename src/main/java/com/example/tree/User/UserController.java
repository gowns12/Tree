package com.example.tree.User;

import com.example.tree.User.Dto.UserLoginResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import java.util.Optional;

@RestController
public class UserController {

    public final UserService userService;
    public final LoginUserResolver loginUserResolver;

    public UserController(UserService userService,LoginUserResolver loginUserResolver) {
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
    public UserLoginResponse loginUser(@Valid @RequestBody UserRequest request){
        return userService.login(request);
    }

//    //회원 수정(PUT/users)(토큰)/RequestBody
//    @Transactional
//    @PutMapping("/users")
//    public UserLoginResponse update(
//            @RequestHeader(HttpHeaders.AUTHORIZATION)String token,
//            @Valid @RequestBody UserRequest request){
//        User user= Optional.ofNullable(LoginUserResolver.resolveUserFromToken(token))
//                .orElseThrow(() -> new RuntimeException("Invalid token or customer not found"));
//
//        return userService.update(request);
//
//    }
}
