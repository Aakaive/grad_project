package com.sparta.grad_project.domain.bookCopy.entity;

import com.sparta.grad_project.domain.book.entity.Book;
import com.sparta.grad_project.domain.bookRental.entity.BookRental;
import com.sparta.grad_project.domain.bookReservation.entity.BookReservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "book_copy")
@NoArgsConstructor
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(nullable = false)
    private LocalDate registeredAt;

    private LocalDate discardedAt;

    private boolean rentable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "bookCopy")
    private List<BookRental> rentalList = new ArrayList<>();

    @OneToMany(mappedBy = "bookCopy")
    private List<BookReservation> reservationList = new ArrayList<>();

    public BookCopy(Book book, LocalDate registeredAt) {
        this.book = book;
        this.registeredAt = registeredAt;
        this.rentable = true;
    }

    public void updateBookCopy(Book book, LocalDate registeredAt, LocalDate discardedAt, boolean rentable) {
        if(book != null) this.book = book;
        if(registeredAt != null) this.registeredAt = registeredAt;
        if(discardedAt != null) this.discardedAt = discardedAt;
        this.rentable = rentable;
    }
}
