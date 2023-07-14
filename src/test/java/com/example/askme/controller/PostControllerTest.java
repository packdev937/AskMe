package com.example.askme.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.askme.domain.Post;
import com.example.askme.repository.PostRepository;
import com.example.askme.request.PostCreateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
//@WebMvcTest // 이걸 하지 않으면 MockMvc가 주입되지 않는다.
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청 시 hello world를 출력한다")
    void test() throws Exception {
        PostCreateDto request = PostCreateDto.builder()
            .title("제목입니다.")
            .content("내용입니다.")
            .build();

        String json = objectMapper.writeValueAsString(request);
        // expected
        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk())
            .andExpect(content().string("{}"))
            .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 title 값은 필수다")
    void test2() throws Exception {
        PostCreateDto request = PostCreateDto.builder()
            .content("내용입니다.")
            .build();

        String json = objectMapper.writeValueAsString(request);


        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
            .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요"))
            .andDo(print());
    }

    // 만약 전체 테스트를 하게 되면 test를 통과할 수 없다.
    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다")
    void test3() throws Exception {
        // when
        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"제목입니다.\", \"content\" : \"내용입니다.\"}"))
            .andExpect(status().isOk())
            .andDo(print());

        // then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }
}

// jsonPath의 검증 방법에 대해 알아보자


// 글 제목
// 글 내용
// 사용자
// id, user, level을 표현하고자 할 때 user의 id, user의 name을 표현하기에 key-value는 한계가 있다 -> x-www-form-urlencoded
// Json은 어떻게 다른가?
/***
 * {
 *  "title" : "xxx",
 *  "content" : "xxx",
 *  "user" : {
 *      "id" : "xxx",
 *      "name" : "xxx",
 *      "level" : "1"
 */

//        mockMvc.perform(post("/posts")
            // application/www-x-form-urlencoded
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("title", "글 제목 입니다.")
//                .param("content", "글 내용 입니다.")) // application/json
            // applicaion/json
            // 처음에 415가 떴는데, Json이 기본값이 아닌데 기본값인줄 알고 명시 안했기 때문
//.contentType(MediaType.APPLICATION_JSON)
