package com.example.tree.letter.service;

import com.example.tree.User.User;
import com.example.tree.User.UserRepository;
import com.example.tree.letter.domain.*;
import com.example.tree.letter.dto.LetterResponse;
import com.example.tree.letter.exception.AccessDeniedException;
import com.example.tree.letter.exception.LetterNotFoundException;
import com.example.tree.tree.Tree;
import com.example.tree.tree.TreeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.example.tree.letter.dto.LetterRequest;
import org.springframework.stereotype.Service;

@Service
public class LetterService {
    private final LetterRepository letterRepository;
    private final TreeRepository treeRepository;
    private final UserRepository userRepository;
    private final LetterDao letterDao;
    private final RecommendDao recommendDao;
    private final RecommendRepository recommendRepository;

    public LetterService(LetterRepository letterRepository, RecommendRepository recommendRepository, TreeRepository treeRepository, UserRepository userRepository, LetterDao letterDao, RecommendDao recommendDao) {
        this.letterRepository = letterRepository;
        this.treeRepository = treeRepository;
        this.userRepository = userRepository;
        this.letterDao = letterDao;
        this.recommendDao = recommendDao;
        this.recommendRepository = recommendRepository;
    }

    //편지 작성
    @Transactional
    public void create(@Valid LetterRequest rq, Long userId) {
        Tree tree = FindEntity.findByTreeId(rq.treeId(), treeRepository);
        User user = FindEntity.findByUserId(userId, userRepository);
        Letter letter = rq.toEntity(tree, user);
        letterRepository.save(letter);
    }

    //편지 조회
    //관리자나 작성자가 아니라면 비공개 편지 조회 불가
    public LetterResponse read(Long letterId, Long userId) {
        Letter letter = letterDao.findByLetterIdAndIsVisible(letterId, userId);
        return LetterResponse.toDto(letter);
    }

    //편지 수정
    //작성자가 아니라면 수정 불가
    @Transactional
    public void update(@Valid LetterRequest rq, Long letterId, Long userId) {
        Letter letter = FindEntity.findByLetterId(letterId, letterRepository);
        if (!letter.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("수정권한이 없는 이용자입니다.");
        }
        letter.update(rq.content(), rq.nickname());
    }

    //편지 삭제
    //관리자나 작성자가 아니라면 삭제불가
    @Transactional
    public void delete(Long letterId, Long userId) {
        Letter letter = FindEntity.findByLetterId(letterId, letterRepository);
        User user = FindEntity.findByUserId(userId, userRepository);
        Tree tree = letter.getTree();
        if (letter.getUser().equals(user) || tree.getUser().equals(user)) {
            letter.delete();
        } else {
            throw new AccessDeniedException("삭제 권한이 없습니다.");
        }
    }

    //편지 공개 상태 변경
    //작성자가 아니라면 변경 불가
    @Transactional
    public void changeIsInvisible(Long letterId, Long userId) {
        Letter letter = FindEntity.findByLetterId(letterId, letterRepository);
        if (!letter.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("변경 권한이 없습니다.");
        } else {
            letter.changeIsVisible();
        }
    }

    //추천 버튼 클릭
    //이미 추천을 누른 사용자가 클릭 시, 추천 취소
    @Transactional
    public void pushRecommend(Long letterId, Long userId) {
        Letter letter = FindEntity.findByLetterId(letterId, letterRepository);
        User user = FindEntity.findByUserId(userId, userRepository);
        if (recommendDao.existsRecommend(letter, user)) {
            Recommend recommend = recommendDao.findByLetterAndUser(letter, user);
            letter.removeRecommend(recommend);
            recommendDao.deleteById(letterId,userId);
        }
        Recommend recommend = new Recommend(letter, user);
        letter.addRecommend(recommend);
        recommendRepository.save(recommend);
    }

    //삭제된 편지 복구
    //관리자나 작성자만 복구 가능
    @Transactional
    public void recoveryLetter(Long letterId, Long userId) {
        Letter letter = FindEntity.findByLetterId(letterId, letterRepository);
        User user = FindEntity.findByUserId(userId, userRepository);
        Tree tree = letter.getTree();
        if (tree.getUser().equals(user) || letter.getUser().equals(user)){
            letter.recover();
        }
        else {
            throw new AccessDeniedException("복구 권한이 없습니다.");
        }
    }

    //리포지토리로 엔티티를 조회하는 클래스
    public static class FindEntity {
        protected static Letter findByLetterId(Long letterId, LetterRepository letterRepository) {
            return letterRepository.findById(letterId).orElseThrow(() -> new LetterNotFoundException("Letter Don't Exists"));
        }

        protected static Tree findByTreeId(Long treeId, TreeRepository treeRepository) {
            return treeRepository.findById(treeId).orElseThrow(() -> new RuntimeException("존재하지 않는 트리"));
        }

        protected static User findByUserId(Long userId, UserRepository userRepository) {
            return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("존재하지 않는 유저"));
        }
    }
}
