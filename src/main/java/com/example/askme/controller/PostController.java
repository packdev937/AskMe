package com.example.askme.controller;

import com.example.askme.request.PostCreateDto;
import com.example.askme.service.PostService;
import jakarta.validation.Valid;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Map<String, String> post(@Valid @RequestBody PostCreateDto request) {
        postService.write(request);
        return Map.of(); // of() 에 대해 알아볼 필요가 있다.
    }

    // 글이 너무 많은 경우 -> 비용이 너무 많이 든다.
    // 글이 10억개 -> DB 글을 모두 조회하는 경우 -> DB가 뻗을 수 있다.
    // DB -> 애플리케이션 서버 쪽으로 전달하는 시간, 데이터(), 트래픽 비용 등이 많이 발생할 수 있다.
    // 따라서 글 전체를 다 조회하는 경우는 없다. (카테고리는 다 긁어오는 경우도 존재한다.)
    // 이를 해결하기 위해 페이징 처리를 배워야 한다.
    // 예를 들어, 요청한 페이지에 대해서 20개, 30개 단위의 포스팅을 가져온다.
    @GetMapping("/posts")
    public List<PostResponse> getList(){
        return postService.getList();
    }
}

// ---
// SSR -> jsp, thymeleaf, mustache, freemarker
// html rendering
// SPA -> vue (vue + SSR = nuxt.js) , react (react+SSR = next.js)
// java script + <-> API (JSON)

// HTTP Method
// GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
// 어떤 기능을 담당하는지에 대한 특징
// 글 등록 -> POST Method

// ---
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

// 데이터 검증을 하는 이유

// 1. client 개발자가 깜빡할 수 있다. 실수로 값을 안보낼 수 있다.
// 2. client bug로 값이 누락될 수 있다
// 3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다
// 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다
// 5. 서버 개발자의 편안함을 위해서

// 데이터 검증은 어떻게 하는가?
// if-else 분기로 처리하는 경우
// 누락 가능성, 검증할 부분이 많다, 개발자 스럽지 않다, 무언가 3번 이상 반복된다면 잘못된 것을 인지해보자
// 검증할 부분이 많다는건 null 값 뿐만 아니라 100글자 초과 등의 조건이 포함될 수 있다
// @Valid를 사용하자

// 하지만 매번 이렇게 코드를 구성하는 것은 불편함이 존재한다
// 항상 메소드에 검증 코드가 들어가야 한다. 즉 반복적인 작업이 들어가야함
//        if(bindingResult.hasErrors()){
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//    FieldError firstFieldError = fieldErrors.get(0);
//    String fieldName = firstFieldError.getField();
//    // default Message는 영어
//    // message 속성을 이용해 이를 custom 할 수 있다.
//    String errorMessage = firstFieldError.getDefaultMessage();
//
//    Map<String, String> error = new HashMap<>();
//    error.put(fieldName, errorMessage);
//    return error;

// @ControllerAdvice
// 1. 매번 메서드마다 값을 검증 해야 한다
//      > 개발자가 까먹을 수 있다
//      > 검증 부분에서 버그가 발생할 가능성이 높다
//      > 지겹다
// 2. 응답 값에 HashMap -> 응답 클래스를 만들어주는 것이 좋다
// 3. 여러 개의 예외 처리가 힘들다
//       > 예를 들어 title에 대한 에러 뿐만 아닌 content에 대한 에러를 처리해야 된다면 또 코드를 작성해야 한다.
// 4. 세 번 이상의 반복적인 작업은 피해야 한다.
//       > 코드 && 개발에 관한 모든 것
// @ControllerAdvice를 통해 개선 가능