package com.sparta.grad_project.domain.auth.exception;


import com.sparta.grad_project.global.exception.GlobalException;

import static com.sparta.grad_project.global.exception.GlobalExceptionConst.INVALID_PASSWORD;


public class InvalidPasswordFormatException extends GlobalException {
    public InvalidPasswordFormatException() {
        super(INVALID_PASSWORD);
    }
}
