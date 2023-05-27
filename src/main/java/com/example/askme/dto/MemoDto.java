package com.example.askme.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemoDto {

    @NotBlank
            @Max(20) // 길이를 제한하는 것이 이게 맞나?
    String question;
    @NotBlank
    String answer;
}
