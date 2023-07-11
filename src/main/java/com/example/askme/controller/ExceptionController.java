package com.example.askme.controller;

import com.example.askme.response.ErrorResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    // MethodArgumentNotValidException가 발생했을 때만 해당 메소드가 실행
    // 처음에는 test가 실행이 안된다. 왜냐하면 JSON 형태로 반환해야 되는데 @RestController가 아닌  @ControllerAdvice 이기 때문에
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        // v1 validation 없이 에러 응답만 리턴할 때
        // return new ErrorResponse("400", "잘못된 요청입니다.");

        // v2 validation 추가
        ErrorResponse response = new ErrorResponse("400", "잘못된 요청입니다.");

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
        // 분기문 안에 return만 있으면 오류
        // 만약 Exception이 발생했는데 에러가 없는 경우를 산정 -> 이게 가능한가?

//        FieldError fieldError = e.getFieldError();
//        String field = fieldError.getField();
//        String message = fieldError.getDefaultMessage();
//
//        Map<String, String> response = new HashMap<>();
//        response.put(field, message);
//        return response;
    }
}
