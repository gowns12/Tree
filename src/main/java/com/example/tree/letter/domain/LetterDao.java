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
    private final QUser qUser;

    public LetterDao(JPAQueryFactory queryFactory, QLetter qLetter, QTree qTree, QUser qUser) {
        this.queryFactory = queryFactory;
        this.qLetter = qLetter;
        this.qUser = qUser;
    }

    //편지ID와 사용자ID를 이용해 편지를 조회,
    //비공개 상태의 편지라면 트리 관리자와 작성자만 조회가능
    public Letter findByLetterIdAndIsVisible(Long letterId, Long userId) {
        Tree tree = queryFactory
                .select(qLetter.tree)
                .from(qLetter)
                .where(qLetter.id.eq(letterId))
                .fetchOne();

        User user = queryFactory
                .select(qLetter.user)
                .from(qLetter)
                .where(qUser.id.eq(userId))
                .fetchOne();

        Letter letter = queryFactory
                .selectFrom(qLetter)
                .where(qLetter.id.eq(letterId))
                .fetchOne();
        //조회를 요청한 사용자가 트리의 주인이거나 작성자라면 무조건 조회 성공
        if (tree.getUser().equals(user)||letter.getUser().equals(user)){
            return queryFactory
                    .selectFrom(qLetter)
                    .where(qLetter.id.eq(letterId))
                    .fetchOne();
        }
        //편지가 공개상태일 경우 조회 성공
        if (letter.getInvisible()){
            return letter;
        }
        //편지가 공개상태가 아니라면 조회불가
        throw new AccessDeniedException("트리주인과 작성자만 볼 수 있는 편지입니다.");
    }
}
