package com.example.tree.letter.domain;

import com.example.tree.User.QUser;
import com.example.tree.User.User;
import com.example.tree.letter.dto.QueryLetterDto;
import com.example.tree.letter.exception.AccessDeniedException;
import com.example.tree.tree.QTree;
import com.example.tree.tree.Tree;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LetterDao {
    private final JPAQueryFactory queryFactory;
    private final QLetter qLetter = QLetter.letter;
    private final QUser qUser = QUser.user;

    public LetterDao(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
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

    public List<QueryLetterDto> findAllByTreeIdOrderBy(Long treeId, String order) {
        OrderSpecifier<?> orderSpecifier = createOrderSpecifier(order);

        List<QueryLetterDto> letterDtos = queryFactory
                .select(
                        Projections.constructor(
                                QueryLetterDto.class,
                                qLetter.id,
                                qLetter.nickname
                        )
                )
                .from(qLetter)
                .where(qLetter.tree.id.eq(treeId).and(qLetter.isInvisible.isTrue()).and(qLetter.isDeleted.isFalse()))
                .orderBy(orderSpecifier)
                .fetch();

        return letterDtos;
    }
    private OrderSpecifier<?> createOrderSpecifier(String order){
        ComparableExpressionBase<?> targetField;
        if ("recommend".equals(order)) {
            targetField = qLetter.recommends.size();
            return new OrderSpecifier<>(Order.DESC,targetField);
        }
        else {
            targetField = qLetter.createdTime;
            return new OrderSpecifier<>(Order.ASC, targetField);
        }
    }
}
