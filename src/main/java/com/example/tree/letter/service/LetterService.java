package com.example.tree.letter.service;

import com.example.tree.User.User;
import com.example.tree.User.UserRepository;
import com.example.tree.letter.domain.LetterDao;
import com.example.tree.letter.dto.LetterResponse;
import com.example.tree.letter.exception.AccessDeniedException;
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
    private final UserRepository userRepository;
    private final LetterDao letterDao;

    public LetterService(LetterRepository letterRepository, TreeRepository treeRepository, UserRepository userRepository, LetterDao letterDao) {
        this.letterRepository = letterRepository;
        this.treeRepository = treeRepository;
        this.userRepository = userRepository;
        this.letterDao = letterDao;
    }

    public void create(@Valid LetterRequest rq) {
        Tree tree = treeRepository.findById(rq.treeId()).orElseThrow(()->new RuntimeException("존재하지 않는 트리"));
        User user = userRepository.findById(rq.userId()).orElseThrow(()->new RuntimeException("존재하지 않는 유저"));
        Letter letter = rq.toEntity(tree,user);
        letterRepository.save(letter);
    }

    public LetterResponse read(Long letterId) {
        Letter letter = letterDao.findByLetterIdAndIsVisible(letterId);
        return LetterResponse.toDto(letter);
    }

    @Transactional
    public void update(@Valid LetterRequest rq,Long letterId) {
        Letter letter = FindEntity.findByLetterId(letterId,letterRepository);
        if (!letter.getUser().getId().equals(rq.userId())){
            throw new AccessDeniedException("수정권한이 없는 이용자입니다.");
        }
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
