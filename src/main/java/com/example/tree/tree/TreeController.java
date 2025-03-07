package com.example.tree.tree;

import com.example.tree.User.LoginUserResolver;
import com.example.tree.User.User;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
public class TreeController {
    private final TreeService treeService;
    private final LoginUserResolver loginUserResolver;

    public TreeController(TreeService treeService, LoginUserResolver loginUserResolver) {
        this.treeService = treeService;
        this.loginUserResolver = loginUserResolver;
    }

    @PostMapping("/trees")
    public void create(@RequestBody createTreeRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        User user = loginUserResolver.resolveUserFromToken(token);
        treeService.create(request, user.getId());
    }

    @GetMapping("/trees/{treeId}")
    public TreeResponse read(@PathVariable Long treeId, @RequestParam(required = false) String order){
        return treeService.read(treeId, order);
    }

    @DeleteMapping("/trees/{treeId}")
    public void delete(@PathVariable Long treeId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        User user = loginUserResolver.resolveUserFromToken(token);
        treeService.delete(treeId, user.getId());
    }
    @PatchMapping("/trees/{treeId}/isClose")
    public void close(@PathVariable Long treeId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        User user = loginUserResolver.resolveUserFromToken(token);
        treeService.close(treeId,user.getId());
    }

}
