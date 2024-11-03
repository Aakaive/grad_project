package com.sparta.grad_project.domain.book.dto;

import com.sparta.grad_project.domain.book.entity.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDtos {
    public BookResponseDtos(Book book) {
        this.bookId = book.getId();
        this.bookTitle = book.getBookTitle();
    }

    private Long bookId;
    private String bookTitle;
}
