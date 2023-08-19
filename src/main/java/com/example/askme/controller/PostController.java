package com.example.askme.controller;

import com.example.askme.domain.Post;
import com.example.askme.domain.PostEditor;
import com.example.askme.request.PostCreateDto;
import com.example.askme.response.PostResponse;
import com.example.askme.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@Valid @RequestBody PostCreateDto request, @RequestHeader String authorization) {
        // POST -> 200, 201
        // Case 1. 저장한 데이터의 Entity -> Response로 응답하기
        // Case 2. 저당한 데이터의 primary Key 응답하기
        // return Map.of("postId", postId);
        //         클라이언트는 수신한 id를 post 조회 API를 통해서 글 데이터를 수신 받음
        // Case 3. 응답 필요 없음 -> 클라이언트에서 모든 POST 데이터 context를 잘 관리함
        // Bad Case : 서버에서 반드시 ~ 로 반환한다고 fix 하는 것
        // 한 번에 일괄적으로 잘 처리되는 케이스가 없으므로 잘 관리하는 형태가 중요하다.
        if(authorization.equals("auth")) {
            postService.write(request);
        }
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    // 글이 너무 많은 경우 -> 비용이 너무 많이 든다.
    // 글이 10억개 -> DB 글을 모두 조회하는 경우 -> DB가 뻗을 수 있다.
    // DB -> 애플리케이션 서버 쪽으로 전달하는 시간, 데이터(), 트래픽 비용 등이 많이 발생할 수 있다.
    // 따라서 글 전체를 다 조회하는 경우는 없다. (카테고리는 다 긁어오는 경우도 존재한다.)
    // 이를 해결하기 위해 페이징 처리를 배워야 한다.
    // 예를 들어, 요청한 페이지에 대해서 20개, 30개 단위의 포스팅을 가져온다.
    @GetMapping("/posts")
    public List<PostResponse> getList(Pageable pageable) { //@RequestParam으로 받으면 주구장창 0이 Default
        return postService.getList(pageable);
    }

    @PatchMapping("/posts/{postId}")
    public PostResponse edit(@PathVariable Long postId, @RequestBody @Valid PostEditor request) {
        return postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public Post delete(@PathVariable Long postId) {
        return postService.delete(postId);
    }
}