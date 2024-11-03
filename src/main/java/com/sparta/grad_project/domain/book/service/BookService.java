package com.sparta.grad_project.domain.book.service;

import com.sparta.grad_project.domain.book.controller.BookController;
import com.sparta.grad_project.domain.book.dto.BookResponseDto;
import com.sparta.grad_project.domain.book.dto.BookRequestDto;
import com.sparta.grad_project.domain.book.dto.BookResponseDtos;
import com.sparta.grad_project.domain.book.entity.Book;
import com.sparta.grad_project.domain.book.exception.AuthorizedAdminException;
import com.sparta.grad_project.domain.book.exception.FindBookException;
import com.sparta.grad_project.domain.book.repository.BookRepository;
import com.sparta.grad_project.domain.user.enums.UserRole;
import com.sparta.grad_project.global.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    public BookResponseDto addBook(BookRequestDto bookRequestDto, UserDetailsImpl userDetails) {
        if (!validateUser(userDetails)) {
            throw new AuthorizedAdminException();
        }

        Book book = new Book(
                bookRequestDto.getIsbn(),
                bookRequestDto.getBookTitle(),
                bookRequestDto.getBookPublished(),
                bookRequestDto.getAuthors(),
                bookRequestDto.getPublishers(),
                bookRequestDto.getSubjects()
        );

        Book savedBook = bookRepository.save(book);

        return new BookResponseDto(savedBook);
    }

    public BookResponseDto updateBook(Long bookId, BookRequestDto bookRequestDto, UserDetailsImpl userDetails) {
        if (!validateUser(userDetails)) {
            throw new AuthorizedAdminException();
        }

        Book book = bookRepository.findById(bookId).orElseThrow(
                FindBookException::new
        );

        book.update(
                bookRequestDto.getBookTitle(),
                bookRequestDto.getBookPublished()
        );

        Book savedBook = bookRepository.save(book);

        return new BookResponseDto(savedBook);
    }

    public Long deleteBook(Long bookId, UserDetailsImpl userDetails) {
        if (!validateUser(userDetails)) {
            throw new AuthorizedAdminException();
        }

        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new FindBookException()
        );

        bookRepository.delete(book);

        return bookId;
    }

    public Boolean validateUser(UserDetailsImpl userDetails) {
        return userDetails.getUser().getRole().equals(UserRole.ROLE_ADMIN);
    }

    public List<BookResponseDtos> getBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(BookResponseDtos::new).toList();
    }

    public BookResponseDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                FindBookException::new
        );
        return new BookResponseDto(book);
    }
}
