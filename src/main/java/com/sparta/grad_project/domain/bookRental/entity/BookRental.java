package com.sparta.grad_project.domain.bookRental.entity;

import com.sparta.grad_project.domain.bookCopy.entity.BookCopy;
import com.sparta.grad_project.domain.bookRental.enums.RentalState;
import com.sparta.grad_project.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "book_rental")
@NoArgsConstructor
public class BookRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private RentalState rentalState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public BookRental(BookCopy bookCopy, User user, LocalDateTime rentalDate) {
        this.bookCopy = bookCopy;
        this.user = user;
        this.rentalDate = rentalDate;
        this.returnDate = null;
        this.rentalState = RentalState.ACTIVE;
    }

    public void updateBookRental() {
        this.returnDate = LocalDateTime.now();
        this.rentalState = RentalState.END;
    }
}
