package com.example.askme.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateDto {
    @NotBlank(message = "타이틀을 입력해주세요")
    public String title;

    @NotBlank(message = "컨텐츠를 입력해주세요")
    public String content;

    @Builder
    public PostCreateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}