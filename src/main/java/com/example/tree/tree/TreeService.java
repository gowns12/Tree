package com.example.tree.tree;

import com.example.tree.User.User;
import com.example.tree.User.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class TreeService {
    private final TreeRepository treeRepository;
    private final UserRepository userRepository;

    public TreeService(TreeRepository treeRepository, UserRepository userRepository, UserRepository userRepository1) {
        this.treeRepository = treeRepository;
        this.userRepository = userRepository1;
    }
    public void create(createTreeRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new NoSuchElementException("userId가 없습니다."));
        treeRepository.save(new Tree(request.title(),user));
    }

    public TreeResponse read(Long treeId) {
        Tree tree = treeRepository.findById(treeId).orElseThrow(() -> new NoSuchElementException("id가 없습니다."));
        return new TreeResponse(tree.getId(),tree.getTitle(),tree.getUrlPath(),tree.isOpen(),tree.getUser().getId(),tree.getLetterList());
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
