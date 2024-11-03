package com.sparta.grad_project.domain.auth.exception;


import com.sparta.grad_project.global.exception.GlobalException;

import static com.sparta.grad_project.global.exception.GlobalExceptionConst.UNAUTHORIZED_PASSWORD;

public class UnauthorizedPasswordException extends GlobalException {
    public UnauthorizedPasswordException() {
        super(UNAUTHORIZED_PASSWORD);
    }
}
