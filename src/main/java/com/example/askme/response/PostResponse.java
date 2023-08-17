package com.example.askme.response;

import com.example.askme.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponse {

    private Long id;

    private String title;

    private String content;

    // getTitle()을 오버라이드하게 되면 repo에 저장할 때 반영이 되나?
    // save() 할 때 Getter를 참조해서 하나?
    public String getTitle() {
        return this.title.length() > 10 ? this.title.substring(0, 10) : this.title;
    }

    public PostResponse(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.title = title;
        this.content = content;
    }
}
