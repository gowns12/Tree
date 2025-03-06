package com.example.tree.tree;

import org.springframework.stereotype.Service;

@Service
public class TreeService {
    private final TreeRepository treeRepository;

    public TreeService(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    public void create(createTreeRequest request) {
        treeRepository.save(new Tree(request.title()));
    }
}
