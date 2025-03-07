package com.example.tree.letter.service;

import com.example.tree.User.LoginUserResolver;
import com.example.tree.User.User;
import com.example.tree.letter.dto.LetterResponse;
import jakarta.validation.Valid;
import com.example.tree.letter.dto.LetterRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/letters")
public class LetterController {
    private final LetterService letterService;
    private final LoginUserResolver loginUserResolver;

    public LetterController(LetterService letterService, LoginUserResolver loginUserResolver) {
        this.letterService = letterService;
        this.loginUserResolver = loginUserResolver;
    }

    //편지 작성
    @PostMapping
    public void create(@RequestBody @Valid LetterRequest rq, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = loginUserResolver.resolveUserFromToken(token);
        letterService.create(rq, user.getId());
    }

    //편지 조회
    @GetMapping("/{letter_id}")
    public LetterResponse read(@PathVariable(name = "letter_id") Long letterId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = loginUserResolver.resolveUserFromToken(token);
        return letterService.read(letterId, user.getId());
    }

    //편지 수정
    @PutMapping("/{letter_id}")
    public void update(@RequestBody @Valid LetterRequest rq, @PathVariable(name = "letter_id") Long letterId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = loginUserResolver.resolveUserFromToken(token);
        letterService.update(rq, letterId, user.getId());
    }

    //편지 삭제
    @DeleteMapping("/{letter_id}")
    public void delete(@PathVariable(name = "letter_id") Long letterId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = loginUserResolver.resolveUserFromToken(token);
        letterService.delete(letterId, user.getId());
    }

    //편지 공개 상태 변경
    @PatchMapping("/change_invisible/{letter_id}")
    public void changeIsInvisible(@PathVariable(name = "letter_id") Long letterId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = loginUserResolver.resolveUserFromToken(token);
        letterService.changeIsInvisible(letterId, user.getId());
    }

    //추천 버튼 클릭
    @PatchMapping("/push_recommend/{letter_id}")
    public void pushRecommend(@PathVariable(name = "letter_id") Long letterId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = loginUserResolver.resolveUserFromToken(token);
        letterService.pushRecommend(letterId, user.getId());
    }

    //삭제된 편지 복구
    @PatchMapping("/recover/{letter_id}")
    public void recoveryLetter(@PathVariable(name = "letter_id") Long letterId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        User user = loginUserResolver.resolveUserFromToken(token);
        letterService.recoveryLetter(letterId, user.getId());
    }
}
