package com.sparta.grad_project.domain.bookRental.exception;

import com.sparta.grad_project.global.exception.GlobalException;
import com.sparta.grad_project.global.exception.GlobalExceptionConst;

public class AlreadyReturnException extends GlobalException {
    public AlreadyReturnException() {super(GlobalExceptionConst.ALREADY_RETURN);}
}
