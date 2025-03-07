package com.example.tree.letter.domain;

import com.example.tree.User.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RecommendDao {
    private final JPAQueryFactory queryFactory;
    private final QRecommend qRecommend = QRecommend.recommend;

    public RecommendDao(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
    //Letter와 User를 이용해 해당 유저가 해당 편지에 이미 추천하였는지 여부 파악
    public Boolean existsRecommend(Letter letter, User user){
        Recommend recommend = queryFactory
                .selectFrom(qRecommend)
                .where(qRecommend.id.letterId.eq(letter.getId()).and(qRecommend.id.userId.eq(user.getId())))
                .fetchFirst();
        return recommend != null;
    }
    ////Letter와 User를 이용해 Recommend 조회
    public Recommend findByLetterAndUser(Letter letter, User user) {
        return queryFactory
                .selectFrom(qRecommend)
                .where(qRecommend.id.letterId.eq(letter.getId()).and(qRecommend.id.userId.eq(user.getId())))
                .fetchOne();
    }

    public void deleteById(Long letterId, Long userId) {
        queryFactory.delete(qRecommend)
                .where(qRecommend.id.letterId.eq(letterId).and(qRecommend.id.userId.eq(userId)))
                .execute();
    }
}
