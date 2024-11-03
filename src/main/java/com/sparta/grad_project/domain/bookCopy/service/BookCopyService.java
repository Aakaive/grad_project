package com.sparta.grad_project.domain.bookCopy.service;

import com.sparta.grad_project.domain.book.entity.Book;
import com.sparta.grad_project.domain.book.exception.AuthorizedAdminException;
import com.sparta.grad_project.domain.book.exception.FindBookException;
import com.sparta.grad_project.domain.book.repository.BookRepository;
import com.sparta.grad_project.domain.bookCopy.dto.BookCopyRequestDto;
import com.sparta.grad_project.domain.bookCopy.dto.BookCopyResponseDto;
import com.sparta.grad_project.domain.bookCopy.entity.BookCopy;
import com.sparta.grad_project.domain.bookCopy.exception.FindBookCopyException;
import com.sparta.grad_project.domain.bookCopy.repository.BookCopyRepository;
import com.sparta.grad_project.domain.user.enums.UserRole;
import com.sparta.grad_project.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCopyService {
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public BookCopyResponseDto addBookCopy(BookCopyRequestDto bookCopyRequestDto, UserDetailsImpl userDetails) {
        if(!validateUserAdmin(userDetails)){
            throw new AuthorizedAdminException();
        }

        Book book = bookRepository.findById(bookCopyRequestDto.getBookId()).orElseThrow(FindBookException::new);
        BookCopy bookCopy = new BookCopy(book, bookCopyRequestDto.getRegisteredAt());

        BookCopy savedBookCopy = bookCopyRepository.save(bookCopy);

        return new BookCopyResponseDto(savedBookCopy.getId(), savedBookCopy.getBook().getBookTitle());
    }

    public BookCopyResponseDto updateBookCopy(BookCopyRequestDto bookCopyRequestDto, UserDetailsImpl userDetails, Long bookCopyId) {
        if(!validateUserAdmin(userDetails)){
            throw new AuthorizedAdminException();
        }

        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(FindBookException::new);

        Book book = bookRepository.findById(bookCopyRequestDto.getBookId()).orElseThrow(FindBookException::new);

        bookCopy.updateBookCopy(book, bookCopyRequestDto.getRegisteredAt(), null, bookCopy.isRentable());

        BookCopy updatedBookCopy = bookCopyRepository.save(bookCopy);

        return new BookCopyResponseDto(updatedBookCopy.getId(), updatedBookCopy.getBook().getBookTitle());
    }

    public Boolean validateUserAdmin(UserDetailsImpl userDetails) {
        return userDetails.getUser().getRole().equals(UserRole.ROLE_ADMIN);
    }

    public Long deleteBookCopy(UserDetailsImpl userDetails, Long bookCopyId) {
        if(!validateUserAdmin(userDetails)){
            throw new AuthorizedAdminException();
        }

        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(FindBookCopyException::new);

        bookCopyRepository.delete(bookCopy);

        return bookCopyId;
    }

    public BookCopyResponseDto getBookCopyById(Long bookCopyId) {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(FindBookCopyException::new);
        return new BookCopyResponseDto(bookCopy.getId(), bookCopy.getBook().getBookTitle());
    }
}
