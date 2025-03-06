package com.example.tree.tree;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TreeController {
    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }



}
