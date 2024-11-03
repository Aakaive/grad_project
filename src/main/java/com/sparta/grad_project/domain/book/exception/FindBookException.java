package com.sparta.grad_project.domain.book.exception;

import com.sparta.grad_project.global.exception.GlobalException;
import com.sparta.grad_project.global.exception.GlobalExceptionConst;

public class FindBookException extends GlobalException {
    public FindBookException() {
        super(GlobalExceptionConst.NOT_FOUND_BOOK);
    }
}
