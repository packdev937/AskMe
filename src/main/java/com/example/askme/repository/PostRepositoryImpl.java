package com.example.askme.repository;

import static com.example.askme.domain.QPost.post;

import com.example.askme.domain.Post;
import com.example.askme.request.PostSearchDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearchDto postSearch) {
        return jpaQueryFactory.selectFrom(post)
            .limit(postSearch.getSize())
            .offset(postSearch.getOffset())
            .orderBy(post.id.desc())
            .fetch();
    }

}
