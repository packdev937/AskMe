package com.example.askme.repository;

import com.example.askme.domain.Post;
import java.util.List;

public interface PostRepositoryCustom {
    List<Post> getList(int page);

}
