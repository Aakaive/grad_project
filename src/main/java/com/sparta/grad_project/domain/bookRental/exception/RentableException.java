package com.sparta.grad_project.domain.bookRental.exception;

import com.sparta.grad_project.global.exception.GlobalException;
import com.sparta.grad_project.global.exception.GlobalExceptionConst;

public class RentableException extends GlobalException {
    public RentableException() {super(GlobalExceptionConst.RENTAL_NOT_POSSIBLE);}
}

