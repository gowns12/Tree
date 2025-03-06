package com.example.tree.tree;

import org.springframework.web.bind.annotation.*;

@RestController
public class TreeController {
    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping("/trees")
    public void create(@RequestBody createTreeRequest request){
        treeService.create(request);
    }

    @GetMapping("/trees/{treeId}")
    public TreeResponse read(@PathVariable Long treeId){
        return treeService.read(treeId);
    }

    @DeleteMapping("/trees/{treeId}")
    public void delete(@PathVariable Long treeId){
        treeService.delete(treeId);

    }

}
