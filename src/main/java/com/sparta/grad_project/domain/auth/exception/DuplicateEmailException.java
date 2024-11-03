package com.sparta.grad_project.domain.auth.exception;

import com.sparta.grad_project.global.exception.GlobalException;

import static com.sparta.grad_project.global.exception.GlobalExceptionConst.DUPLICATE_EMAIL;

public class DuplicateEmailException extends GlobalException {
    public DuplicateEmailException() {
        super(DUPLICATE_EMAIL);
    }
}
