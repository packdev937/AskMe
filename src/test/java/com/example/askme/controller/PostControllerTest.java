package com.example.askme.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest // 이걸 하지 않으면 MockMvc가 주입되지 않는다.
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/posts 요청 시 hello world를 출력한다")
    void test() throws Exception {
        // expected
        mockMvc.perform(get("/posts"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello World"));
    }
}