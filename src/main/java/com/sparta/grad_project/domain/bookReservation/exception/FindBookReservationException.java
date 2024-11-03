package com.sparta.grad_project.domain.bookReservation.exception;

import com.sparta.grad_project.global.exception.GlobalException;
import com.sparta.grad_project.global.exception.GlobalExceptionConst;

public class FindBookReservationException extends GlobalException {
    public FindBookReservationException() {
        super(GlobalExceptionConst.NOT_FOUND_BOOK_RESERVATION);
    }
}
