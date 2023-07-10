package com.example.askme.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @GetMapping("/posts")
    public String get(){
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