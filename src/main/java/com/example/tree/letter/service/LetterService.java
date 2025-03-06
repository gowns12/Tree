package com.example.tree.letter.service;

import com.example.tree.letter.dto.LetterResponse;
import com.example.tree.letter.exception.LetterNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.example.tree.letter.domain.Letter;
import com.example.tree.letter.dto.LetterRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterService {
    private final LetterRepository letterRepository;
    private final FindEntity findEntity;

    public LetterService(LetterRepository letterRepository, FindEntity findEntity) {
        this.letterRepository = letterRepository;
        this.findEntity = findEntity;
    }

    public void create(@Valid LetterRequest rq) {
        Letter letter = new Letter(rq.content(), rq.nickname());
        letterRepository.save(letter);
    }

    public List<LetterResponse> readAll(Long treeId) {
        return letterRepository.findAllByTreeId(treeId).stream()
                .map(LetterResponse::toDto)
                .toList();
    }

    public LetterResponse read(Long letterId) {
        Letter letter = findEntity.findByLetterId(letterId);
        return LetterResponse.toDto(letter);
    }

    @Transactional
    public void update(@Valid LetterRequest rq,Long letterId) {
        Letter letter = findEntity.findByLetterId(letterId);
        letter.update(rq.content(),rq.nickname());
    }

    @Transactional
    public void delete(Long letterId) {
        Letter letter = findEntity.findByLetterId(letterId);
        letter.delete();
    }

    public class FindEntity {
        protected Letter findByLetterId(Long letterId){
            return letterRepository.findById(letterId).orElseThrow(()->new LetterNotFoundException("Letter Don't Exists"));
        }
    }
}
