package com.example.tree.letter.service;

import com.example.tree.letter.dto.LetterResponse;
import jakarta.validation.Valid;
import com.example.tree.letter.dto.LetterRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/letter")
public class LetterController {
    private final LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @PostMapping
    public void LetterCreate(@RequestBody@Valid LetterRequest rq){
        letterService.create(rq);
    }

    @GetMapping("/{tree_id}")
    public List<LetterResponse> readAll(@PathVariable(name = "tree_id") Long treeId){
        return letterService.readAll(treeId);
    }

    @GetMapping("/{letter_id}")
    public LetterResponse read(@PathVariable(name = "letter_id") Long letterId){
        return letterService.read(letterId);
    }

    @PutMapping("/{letter_id}")
    public void update(@RequestBody@Valid LetterRequest rq){
        letterService.update(rq);
    }
}
