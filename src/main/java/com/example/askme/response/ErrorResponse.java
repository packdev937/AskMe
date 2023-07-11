package com.example.askme.response;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    // 가능하면 Map을 안쓰는 것이 좋다.
    private List<ValidationTuple> validation = new LinkedList<>();

    public void addValidation(String fieldName, String errorMessage) {
        validation.add(new ValidationTuple(fieldName, errorMessage));
    }

    @RequiredArgsConstructor
    @Getter
    private class ValidationTuple {
        private final String fieldName;
        private final String errorMessage;
    }
}
