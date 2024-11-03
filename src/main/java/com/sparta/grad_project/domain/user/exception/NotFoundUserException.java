package com.sparta.grad_project.domain.user.exception;


import com.sparta.grad_project.global.exception.GlobalException;

import static com.sparta.grad_project.global.exception.GlobalExceptionConst.NOT_FOUND_USER;


public class NotFoundUserException extends GlobalException {
    public NotFoundUserException() {
        super(NOT_FOUND_USER);
    }
}