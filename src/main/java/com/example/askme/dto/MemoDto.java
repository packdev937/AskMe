package com.example.askme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemoDto {

    @NotBlank
    @Size(min = 2, max = 100, message = "질문은 20자를 넘길 수 없습니다.") // Max -> Size
    String question;
    @NotBlank
    String answer;
}
