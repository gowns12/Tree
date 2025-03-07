package com.example.tree.tree;

import com.example.tree.User.QUser;
import com.example.tree.letter.domain.QLetter;
import com.example.tree.tree.exception.TreeIsNotOpenException;
import com.example.tree.tree.exception.TreeNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.management.Query;

@Repository
public class TreeDao {
    private final JPAQueryFactory queryFactory;
    private final QTree qTree = QTree.tree;
    public TreeDao(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Tree findByTreeIdAndIsOpen(Long treeId){
        Tree tree = queryFactory
                .selectFrom(qTree)
                .where(qTree.id.eq(treeId))
                .fetchOne();

        if (tree == null){
            throw new TreeNotFoundException("존재하지 않는 트리입니다.");
        }
        if (!tree.isOpen()){
            throw new TreeIsNotOpenException("비공개 상태인 트리입니다.");
        }
        return tree;
    }
}
