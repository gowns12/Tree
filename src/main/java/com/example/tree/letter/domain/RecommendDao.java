package com.example.tree.letter.domain;

import com.example.tree.User.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RecommendDao {
    private final JPAQueryFactory queryFactory;
    private final QRecommend qRecommend;

    public RecommendDao(JPAQueryFactory queryFactory, QRecommend qRecommend, QLetter qLetter) {
        this.queryFactory = queryFactory;
        this.qRecommend = qRecommend;
    }

    public Boolean existsRecommend(Long letterId, Long userId){
        Recommend recommend = queryFactory
                .selectFrom(qRecommend)
                .where(qRecommend.id.letter.id.eq(letterId).and(qRecommend.id.user.id.eq(userId)))
                .fetchFirst();
        return recommend != null;
    }

    public Recommend findByLetterAndUser(Letter letter, User user) {
        return queryFactory
                .selectFrom(qRecommend)
                .where(qRecommend.id.letter.eq(letter).and(qRecommend.id.user.eq(user)))
                .fetchOne();
    }
}
