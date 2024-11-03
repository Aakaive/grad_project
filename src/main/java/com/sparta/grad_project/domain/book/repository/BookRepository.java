package com.sparta.grad_project.domain.book.repository;

import com.sparta.grad_project.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
}
