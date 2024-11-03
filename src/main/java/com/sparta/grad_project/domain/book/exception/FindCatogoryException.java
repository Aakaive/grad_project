package com.sparta.grad_project.domain.book.exception;

import com.sparta.grad_project.global.exception.GlobalException;
import com.sparta.grad_project.global.exception.GlobalExceptionConst;

public class FindCatogoryException extends GlobalException {
    public FindCatogoryException() {
        super(GlobalExceptionConst.NOT_FOUND_CATEGORY);
    }
}
