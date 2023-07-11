package com.example.askme.controller;

import com.example.askme.request.PostCreateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PostController {

    // Version 1
//    @PostMapping("/posts")
//    public String post(@RequestParam String title, @RequestParam String content){
//        log.info("title={}, content={}", title, content);
//        return "Hello World";
//    }

    // Version 2
//    @PostMapping("/posts")
//    public String post(@RequestParam Map<String, String> params){
//        log.info("params={}", params);
//        return "Hello World";
//    }

    // Version 3 Data에 대한 클래스 형식 (DTO)를 새롭게 만드는 것
    // application/x-www-form-urlencoded 가 아닌 application/json은 @RequestBody를 붙여줘야함
    @PostMapping("/posts")
    public String post(@RequestBody PostCreateDto postCreateDto){
        log.info("postCreateDto={}", postCreateDto.toString());
        return "Hello World";
    }
}
// SSR -> jsp, thymeleaf, mustache, freemarker
// html rendering
// SPA -> vue (vue + SSR = nuxt.js) , react (react+SSR = next.js)
// java script + <-> API (JSON)

// HTTP Method
// GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
// 어떤 기능을 담당하는지에 대한 특징
// 글 등록 -> POST Method