package com.sparta.grad_project.domain.bookCopy.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCopyResponseDto {
    private Long bookCopyId;
    private String BookTitle;

    public BookCopyResponseDto(Long bookCopyId, String booktitle) {
        this.bookCopyId = bookCopyId;
        BookTitle = booktitle;
    }
}
