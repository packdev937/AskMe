package com.example.askme.request;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class PostCreateDto {
    public String title;
    public String content;
}

// Setter가 없으니깐 DTO로 받으면 값이 null이 들어갔다. -> applicaion/x-www-form-urlencoded
// applicaion/json은 ? 없어도 된다
