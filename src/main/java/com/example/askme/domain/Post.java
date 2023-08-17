package com.example.askme.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob // @Lob이 뭔가? DB에서는 Long Text의 형태로 생성되도록
    private String content;

    public void edit(PostEditor postEditor) {
        this.title = postEditor.getTitle();
        this.content = postEditor.getContent();
    }

    // 서비스의 정책을 넣지 말자!
    // 예를 들어 글자 수 10글자만 리턴해주는 것
    // 유사 기능의 다른 서비스를 구현하는데 혼란이 올 수 있다.
    // 그럼 어떻게 하냐? 응답 클래스를 분리해라
}
