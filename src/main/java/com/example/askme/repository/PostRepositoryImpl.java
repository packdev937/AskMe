package com.example.askme.repository;

import com.example.askme.domain.Post;
import com.example.askme.domain.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(int page) {
        return jpaQueryFactory.selectFrom(QPost.post)
            .limit(10)
            .offset((long) (page - 1) * 10)
            .fetch();
    }
}
