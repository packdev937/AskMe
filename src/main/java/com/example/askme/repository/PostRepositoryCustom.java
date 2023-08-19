package com.example.askme.repository;

import com.example.askme.domain.Post;
import com.example.askme.request.PostSearchDto;
import java.util.List;

public interface PostRepositoryCustom {
    List<Post> getList(PostSearchDto postSearch);
}
