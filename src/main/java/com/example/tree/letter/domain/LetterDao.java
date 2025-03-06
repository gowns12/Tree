package com.example.tree.letter.domain;

import com.example.tree.User.QUser;
import com.example.tree.User.User;
import com.example.tree.letter.exception.AccessDeniedException;
import com.example.tree.tree.QTree;
import com.example.tree.tree.Tree;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class LetterDao {
    private final JPAQueryFactory queryFactory;
    private final QLetter qLetter;
    private final QTree qTree;
    private final QUser qUser;

    public LetterDao(JPAQueryFactory queryFactory, QLetter qLetter, QTree qTree, QUser qUser) {
        this.queryFactory = queryFactory;
        this.qLetter = qLetter;
        this.qTree = qTree;
        this.qUser = qUser;
    }


    public Letter findByLetterIdAndIsVisible(Long letterId) {
        Tree tree = queryFactory
                .select(qLetter.tree)
                .from(qLetter)
                .where(qLetter.id.eq(letterId))
                .fetchOne();

        User user = queryFactory
                .select(qLetter.user)
                .from(qLetter)
                .where(qLetter.id.eq(letterId))
                .fetchOne();

        Letter letter = queryFactory
                .selectFrom(qLetter)
                .where(qLetter.id.eq(letterId))
                .fetchOne();

        if (tree.getUser().equals(user)||letter.getUser().equals(user)){
            return queryFactory
                    .selectFrom(qLetter)
                    .where(qLetter.id.eq(letterId))
                    .fetchOne();
        }

        if (letter.getInvisible()){
            return letter;
        }
        throw new AccessDeniedException("트리주인과 작성자만 볼 수 있는 편지입니다.");
    }
}
