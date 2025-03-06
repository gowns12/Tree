package com.example.tree.letter.service;

import com.example.tree.letter.dto.LetterResponse;
import jakarta.validation.Valid;
import com.example.tree.letter.dto.LetterRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/letters")
public class LetterController {
    private final LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @PostMapping
    public void LetterCreate(@RequestBody@Valid LetterRequest rq){
        letterService.create(rq);
    }

    @GetMapping("/{letter_id}")
    public LetterResponse read(@PathVariable(name = "letter_id") Long letterId){
        return letterService.read(letterId);
    }

    @PutMapping("/{letter_id}")
    public void update(@RequestBody@Valid LetterRequest rq, @PathVariable(name = "letter_id") Long letterId){
        letterService.update(rq, letterId);
    }

    @DeleteMapping("/{letter_id}")
    public void delete(@PathVariable(name = "letter_id") Long letterId){
        letterService.delete(letterId);
    }

    @PatchMapping("/{letter_id}")
    public void changeIsInvisible(@PathVariable(name = "letter_id") Long letterId){
        letterService.changeIsInvisible(letterId);
    }

    @PatchMapping("/{letter_id}")
    public void pushRecommend(@PathVariable(name = "letter_id") Long letterId, Long userId){
        letterService.pushRecommend(letterId, userId);
    }
}
