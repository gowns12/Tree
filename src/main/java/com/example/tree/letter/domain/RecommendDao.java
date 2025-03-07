//package com.example.tree.letter.domain;
//
//import com.example.tree.User.User;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class RecommendDao {
//    private final JPAQueryFactory queryFactory;
//    private final QRecommend qRecommend;
//
//    public RecommendDao(JPAQueryFactory queryFactory, QRecommend qRecommend, QLetter qLetter) {
//        this.queryFactory = queryFactory;
//        this.qRecommend = qRecommend;
//    }
//    //Letter와 User를 이용해 해당 유저가 해당 편지에 이미 추천하였는지 여부 파악
//    public Boolean existsRecommend(Letter letter, User user){
//        Recommend recommend = queryFactory
//                .selectFrom(qRecommend)
//                .where(qRecommend.id.letter.eq(letter).and(qRecommend.id.user.eq(user)))
//                .fetchFirst();
//        return recommend != null;
//    }
//    ////Letter와 User를 이용해 Recommend 조회
//    public Recommend findByLetterAndUser(Letter letter, User user) {
//        return queryFactory
//                .selectFrom(qRecommend)
//                .where(qRecommend.id.letter.eq(letter).and(qRecommend.id.user.eq(user)))
//                .fetchOne();
//    }
//}
