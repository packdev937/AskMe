package com.example.askme.service;

import com.example.askme.domain.Post;
import com.example.askme.repository.PostRepository;
import com.example.askme.request.PostCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreateDto postCreateDto) {
        Post post = new Post(postCreateDto.getTitle(), postCreateDto.getContent());
        postRepository.save(post);
    }
}
