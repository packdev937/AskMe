package com.example.askme.service;

import com.example.askme.domain.Post;
import com.example.askme.domain.PostEditor;
import com.example.askme.repository.PostRepository;
import com.example.askme.request.PostCreateDto;
import com.example.askme.response.PostResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public Post write(PostCreateDto postCreateDto) {
        Post post = Post.builder()
            .title(postCreateDto.getTitle())
            .content(postCreateDto.getContent())
            .build();
        return postRepository.save(post);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> {
                throw new IllegalArgumentException("회원이 존재하지 않습니다");
            });

        return new PostResponse(post);
    }

    public List<PostResponse> getList(Pageable pageable) {
        return postRepository.findAll(pageable).stream()
            .map(PostResponse::new)
            // 반복적인 작업
//                PostResponse.builder()
//                .id(post.getId())
//                .title(post.getTitle())
//                .content(post.getContent())
//                .build())
            .collect(Collectors.toList());
    }

    @Transactional
    public PostResponse edit(Long id, PostEditor postEditor) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        PostEditor.PostEditorBuilder editBuilder = PostEditor.builder();
        if (postEditor.getTitle() != null) {
            editBuilder.title(postEditor.getTitle());
        }

        if (postEditor.getContent() != null) {
            editBuilder.content(postEditor.getContent());
        }

        editBuilder.build();

        post.edit(postEditor);
        return new PostResponse(post);
    }

    @Transactional
    public Post delete(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        postRepository.delete(post);
        return post;
    }
}
