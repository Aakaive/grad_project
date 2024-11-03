package com.sparta.grad_project.domain.book.exception;

import com.sparta.grad_project.global.exception.GlobalException;
import com.sparta.grad_project.global.exception.GlobalExceptionConst;

public class AuthorizedAdminException extends GlobalException {
    public AuthorizedAdminException() {
        super(GlobalExceptionConst.UNAUTHORIZED_ADMIN);
    }
}
