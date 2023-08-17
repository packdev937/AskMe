package com.example.askme.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.askme.domain.Post;
import com.example.askme.repository.PostRepository;
import com.example.askme.response.PostResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class PostServiceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @BeforeEach
    void clean() {
        // 매번 repository를 초기화
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        // given
        // Builder로 Post 객체 생성
        // when
        // service에 저장
        // then
        // repository에 잘 저장되었는지 확인
        // reppsitory.count()
        // 혹은 값을 꺼내와서 넣은 값과 비교
    }

    @Test
    @DisplayName("첫 1 페이지 글 조회")
    void test2() {
        // given
        List<Post> requestPosts = IntStream.range(0, 30)
            .mapToObj(i -> {
                return Post.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .build();
            }).collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        // when
        // 1페이지만 나오도록
        Pageable pageable = PageRequest.of(0, 5, Direction.DESC, "id");
        List<PostResponse> posts = postService.getList(pageable);

        // then
        assertEquals(5L, posts.size());
    }

    @Test
    @DisplayName("페이지 조회")
    void test3() throws Exception {
        //given
        List<Post> requestPosts = IntStream.range(0, 30)
            .mapToObj(i -> {
                return Post.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .build();
            }).collect(Collectors.toList());
        postRepository.saveAll(requestPosts);

        // then
        mockMvc.perform(
                get("/posts?page=1&sort=id,desc&size=5") // pageable을 넘기기 때문에 속성을 지정해야됨, application.yml 에 설정했으면 @DefaultPageable 뺀다
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("글 제목 수정")
    void test4() throws Exception {

    }

    @Test
    @DisplayName("글 조회 실패")
    void test5() {
        // given
        Post post = Post.builder()
            .title("제목")
            .content("글")
            .build();

        // expected
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postService.get(post.getId()+1L);
        });

    }
    // sql -> select, limit, offset
}