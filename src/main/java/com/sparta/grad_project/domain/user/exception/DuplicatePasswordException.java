package com.sparta.grad_project.domain.user.exception;


import com.sparta.grad_project.global.exception.GlobalException;

import static com.sparta.grad_project.global.exception.GlobalExceptionConst.DUPLICATE_PASSWORD;


public class DuplicatePasswordException extends GlobalException {
    public DuplicatePasswordException() {
        super(DUPLICATE_PASSWORD);
    }
}
