package com.example.tree.tree;

import com.example.tree.User.User;
import com.example.tree.User.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class TreeService {
    private final TreeRepository treeRepository;

    public TreeService(TreeRepository treeRepository, UserRepository userRepository) {
        this.treeRepository = treeRepository;

    }

    public void create(createTreeRequest request) {
        treeRepository.save(new Tree(request.title()));
    }

    public TreeResponse read(Long treeId) {
        Tree tree = treeRepository.findById(treeId).orElseThrow(() -> new NoSuchElementException("id가 없습니다."));
        return new TreeResponse(tree.getId(),tree.getTitle(),tree.getUser().getId(),tree.getLetterList());
    }
    @Transactional
    public void delete(Long treeId) {
        treeRepository.deleteById(treeId);
    }
}
