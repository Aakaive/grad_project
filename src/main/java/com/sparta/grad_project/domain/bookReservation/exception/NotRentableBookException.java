package com.sparta.grad_project.domain.bookReservation.exception;

import com.sparta.grad_project.global.exception.GlobalException;
import com.sparta.grad_project.global.exception.GlobalExceptionConst;

public class NotRentableBookException extends GlobalException {
    public NotRentableBookException() {
        super(GlobalExceptionConst.NOT_FOUND_RENTABLE_BOOKCOPY);
    }
}
