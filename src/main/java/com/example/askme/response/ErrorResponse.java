package com.example.askme.response;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***
 * {
 *     "code" : "400",
 *     "message" : "잘못된 요청입니다.",
 *     "validation" : {
 *         "title" : "값을 입력해주세요."
 *     }
 * }
 */

// 하지만 만약 validation이 없다면 어떤 값이 작성되지 않았는지 확인할 수 없음
@RequiredArgsConstructor
@Getter
public class ErrorResponse {

    private final String code;
    private final String message;

    private Map<String, String> validation = new HashMap<>();

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
