package com.sparta.grad_project.domain.bookRental.exception;

import com.sparta.grad_project.global.exception.GlobalException;
import com.sparta.grad_project.global.exception.GlobalExceptionConst;

public class NotFoundBookRenalHistoryException extends GlobalException {
    public NotFoundBookRenalHistoryException() {super(GlobalExceptionConst.NOT_FOUND_BOOK_RENTAL);}
}
