package com.example.askme.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class PostCreateDto {
    @NotBlank(message = "타이틀을 입력해주세요")
    public String title;

    @NotBlank(message = "컨텐츠를 입력해주세요")
    public String content;
}

// Setter가 없으니깐 DTO로 받으면 값이 null이 들어갔다. -> applicaion/x-www-form-urlencoded
// applicaion/json은 ? 없어도 된다
