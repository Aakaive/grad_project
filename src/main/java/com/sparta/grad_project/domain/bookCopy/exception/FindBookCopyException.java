package com.sparta.grad_project.domain.bookCopy.exception;

import com.sparta.grad_project.global.exception.GlobalException;
import com.sparta.grad_project.global.exception.GlobalExceptionConst;

public class FindBookCopyException extends GlobalException {
    public FindBookCopyException() {
        super(GlobalExceptionConst.NOT_FOUND_BOOK_COPY);
    }
}
