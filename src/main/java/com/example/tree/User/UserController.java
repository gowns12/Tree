package com.example.tree.User;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입(POST/users/signup)RequestBody/valid
    @PostMapping("/users/signup")
    public void create(@Valid @RequestBody UserRequest request) {
        userService.save(request);
    }
}
