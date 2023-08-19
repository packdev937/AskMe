package com.example.askme.service;

import com.example.askme.domain.Post;
import com.example.askme.domain.PostEditor;
import com.example.askme.exception.PostNotFoundException;
import com.example.askme.repository.PostRepository;
import com.example.askme.request.PostCreateDto;
import com.example.askme.request.PostEditDto;
import com.example.askme.request.PostSearchDto;
import com.example.askme.response.PostResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreateDto postCreate) {
        Post post = Post.builder()
            .title(postCreate.getTitle())
            .content(postCreate.getContent())
            .build();

        postRepository.save(post);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(PostNotFoundException::new);

        return PostResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .build();
    }

    public List<PostResponse> getList(PostSearchDto postSearch) {
        return postRepository.getList(postSearch).stream()
            .map(PostResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEditDto postEdit) {
        Post post = postRepository.findById(id)
            .orElseThrow(PostNotFoundException::new);

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        PostEditor postEditor = editorBuilder.title(postEdit.getTitle())
            .content(postEdit.getContent())
            .build();

        post.edit(postEditor);
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(PostNotFoundException::new);

        postRepository.delete(post);
    }
}

