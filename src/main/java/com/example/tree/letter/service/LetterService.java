package com.example.tree.letter.service;

import com.example.tree.letter.dto.LetterResponse;
import com.example.tree.letter.exception.LetterNotFoundException;
import com.example.tree.tree.Tree;
import com.example.tree.tree.TreeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.example.tree.letter.domain.Letter;
import com.example.tree.letter.dto.LetterRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterService {
    private final LetterRepository letterRepository;
    private final TreeRepository treeRepository;

    public LetterService(LetterRepository letterRepository, TreeRepository treeRepository) {
        this.letterRepository = letterRepository;
        this.treeRepository = treeRepository;
    }

    public void create(@Valid LetterRequest rq) {
        Tree tree = treeRepository.findById(rq.treeId()).orElseThrow(()->new RuntimeException("존재하지 않는 트리"));
        Letter letter = new Letter(rq.content(), rq.nickname(),tree);
        letterRepository.save(letter);
    }

    public List<LetterResponse> readAll(Long treeId) {
        return letterRepository.findAllByTree_Id(treeId).stream()
                .map(LetterResponse::toDto)
                .toList();
    }

    public LetterResponse read(Long letterId) {
        Letter letter = FindEntity.findByLetterId(letterId,letterRepository);
        return LetterResponse.toDto(letter);
    }

    @Transactional
    public void update(@Valid LetterRequest rq,Long letterId) {
        Letter letter = FindEntity.findByLetterId(letterId,letterRepository);
        letter.update(rq.content(),rq.nickname());
    }

    @Transactional
    public void delete(Long letterId) {
        Letter letter = FindEntity.findByLetterId(letterId,letterRepository);
        letter.delete();
    }

    public static class FindEntity {
        protected static Letter findByLetterId(Long letterId,LetterRepository letterRepository){
            return letterRepository.findById(letterId).orElseThrow(()->new LetterNotFoundException("Letter Don't Exists"));
        }
    }
}
