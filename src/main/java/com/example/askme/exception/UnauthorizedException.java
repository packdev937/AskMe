package com.example.askme.exception;

public class UnauthorizedException extends RuntimeException{
    private static final String MESSAGE = "인증이 필요합니다";

    public UnauthorizedException() {
        super(MESSAGE);
    }

    public int getStatusCode(){
        return 401;
    }
}
