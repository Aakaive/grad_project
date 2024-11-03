package com.sparta.grad_project.domain.review.exception;

import com.sparta.grad_project.global.exception.GlobalException;

import static com.sparta.grad_project.global.exception.GlobalExceptionConst.UNAUTHORIZED_CREATE_REVIEW;

public class ReviewAccessForbiddenException extends GlobalException {

    public ReviewAccessForbiddenException(){
        super(UNAUTHORIZED_CREATE_REVIEW);
    }
}
