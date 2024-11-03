package com.sparta.grad_project.domain.bookCopy.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookCopyRequestDto {
    private Long bookId;
    private LocalDate registeredAt;
}
