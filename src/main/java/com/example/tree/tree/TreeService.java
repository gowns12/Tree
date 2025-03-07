package com.example.tree.tree;

import com.example.tree.User.User;
import com.example.tree.User.UserRepository;
import com.example.tree.letter.domain.Letter;
import com.example.tree.letter.domain.LetterDao;
import com.example.tree.letter.dto.LetterSimpleResponse;
import com.example.tree.letter.dto.QueryLetterDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TreeService {
    private final TreeRepository treeRepository;
    private final UserRepository userRepository;
    private final TreeDao treeDao;
    private final LetterDao letterDao;

    public TreeService(TreeDao treeDao, LetterDao letterDao, TreeRepository treeRepository, UserRepository userRepository, UserRepository userRepository1) {
        this.treeDao = treeDao;
        this.treeRepository = treeRepository;
        this.userRepository = userRepository1;
        this.letterDao = letterDao;
    }
    public void create(createTreeRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new NoSuchElementException("userId가 없습니다."));
        treeRepository.save(new Tree(request.title(),user));
    }

    public TreeResponse read(Long treeId, String order) {
        Tree tree = treeDao.findByTreeIdAndIsOpen(treeId);
        List<LetterSimpleResponse> letterSRPList = letterDao.findAllByTreeIdOrderBy(treeId, order).stream()
                .map(LetterSimpleResponse::toDto)
                .toList();
        return new TreeResponse(tree.getId(),tree.getTitle(),tree.getUrlPath(),tree.isOpen(),tree.getUser().getId(),letterSRPList);
    }
    @Transactional
    public void delete(Long treeId) {
        treeRepository.deleteById(treeId);
    }
    @Transactional
    public void close(Long treeId) {
        Tree tree = treeRepository.findById(treeId).orElseThrow(() -> new NoSuchElementException("id가 없습니다."));
        tree.isClose();
    }
}
