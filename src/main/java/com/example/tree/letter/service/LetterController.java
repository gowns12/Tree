//package com.example.tree.letter.service;
//
//import com.example.tree.letter.dto.LetterResponse;
//import jakarta.validation.Valid;
//import com.example.tree.letter.dto.LetterRequest;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/letters")
//public class LetterController {
//    private final LetterService letterService;
//
//    public LetterController(LetterService letterService) {
//        this.letterService = letterService;
//    }
//
//    //편지 작성
//    @PostMapping
//    public void create(@RequestBody @Valid LetterRequest rq, Long userId) {
//        letterService.create(rq, userId);
//    }
//
//    //편지 조회
//    @GetMapping("/{letter_id}")
//    public LetterResponse read(@PathVariable(name = "letter_id") Long letterId, Long userId) {
//        return letterService.read(letterId, userId);
//    }
//
//    //편지 수정
//    @PutMapping("/{letter_id}")
//    public void update(@RequestBody @Valid LetterRequest rq, @PathVariable(name = "letter_id") Long letterId, Long userId) {
//        letterService.update(rq, letterId, userId);
//    }
//
//    //편지 삭제
//    @DeleteMapping("/{letter_id}")
//    public void delete(@PathVariable(name = "letter_id") Long letterId, Long userId) {
//        letterService.delete(letterId, userId);
//    }
//
//    //편지 공개 상태 변경
//    @PatchMapping("/{letter_id}")
//    public void changeIsInvisible(@PathVariable(name = "letter_id") Long letterId, Long userId) {
//        letterService.changeIsInvisible(letterId, userId);
//    }
//
//    //추천 버튼 클릭
//    @PatchMapping("/{letter_id}")
//    public void pushRecommend(@PathVariable(name = "letter_id") Long letterId, Long userId) {
//        letterService.pushRecommend(letterId, userId);
//    }
//
//    @PatchMapping("/{letter_id}")
//    public void recoveryLetter(@PathVariable(name = "letter_id") Long letterId, Long userId){
//        letterService.recoveryLetter(letterId, userId);
//    }
//}
