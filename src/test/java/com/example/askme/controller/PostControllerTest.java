package com.example.askme.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest // 이걸 하지 않으면 MockMvc가 주입되지 않는다.
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/posts 요청 시 hello world를 출력한다")
    void test() throws Exception {
        // 글 제목
        // 글 내용
        // 사용자
            // id, user, level을 표현하고자 할 때 user의 id, user의 name을 표현하기에 key-value는 한계가 있다

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

        // expected
        mockMvc.perform(post("/posts")
            // application/www-x-form-urlencoded
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("title", "글 제목 입니다.")
//                .param("content", "글 내용 입니다.")) // application/json
            // applicaion/json
            // 처음에 415가 떴는데, Json이 기본값이 아닌데 기본값인줄 알고 명시 안했기 때문
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"제목입니다.\", \"content\" : \"내용입니다.\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string("{}"))
            .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 title 값은 필수다")
    void test2() throws Exception {
        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
            // title이 blank가 아닌 Null 값일 때도 동일하다
                .content("{\"title\":null, \"content\" : \"내용입니다.\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
            .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
            .andDo(print());
    }
}

// jsonPath의 검증 방법에 대해 알아보자
