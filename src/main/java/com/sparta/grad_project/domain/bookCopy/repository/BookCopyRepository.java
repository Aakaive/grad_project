package com.sparta.grad_project.domain.bookCopy.repository;

import com.sparta.grad_project.domain.book.entity.Book;
import com.sparta.grad_project.domain.bookCopy.entity.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    List<BookCopy> findAllByBookAndRentableTrue(Book book);
}
