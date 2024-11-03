package com.sparta.grad_project.domain.review.exception;

import com.sparta.grad_project.global.exception.GlobalException;

import static com.sparta.grad_project.global.exception.GlobalExceptionConst.INVALID_REVIEWSTAR;

public class InvalidReviewStarException extends GlobalException {

    public InvalidReviewStarException(){
       super(INVALID_REVIEWSTAR);

    }
}
