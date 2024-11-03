package com.sparta.grad_project.domain.review.exception;

import com.sparta.grad_project.global.exception.GlobalException;
import com.sparta.grad_project.global.exception.GlobalExceptionConst;

public class ReviewNotFoundException extends GlobalException {

    public ReviewNotFoundException() {
        super(GlobalExceptionConst.NOT_FOUND_REVIEW);
    }
}
