package com.sparta.grad_project.domain.auth.exception;


import com.sparta.grad_project.global.exception.GlobalException;

import static com.sparta.grad_project.global.exception.GlobalExceptionConst.DELETED_USER;

public class DeletedUserException extends GlobalException {
    public DeletedUserException() {
        super(DELETED_USER);
    }
}
