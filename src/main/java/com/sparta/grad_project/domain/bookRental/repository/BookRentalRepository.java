package com.sparta.grad_project.domain.bookRental.repository;

import com.sparta.grad_project.domain.bookCopy.entity.BookCopy;
import com.sparta.grad_project.domain.bookRental.entity.BookRental;
import com.sparta.grad_project.domain.bookRental.enums.RentalState;
import com.sparta.grad_project.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {


    Optional<BookRental> findByBookCopyAndRentalState(BookCopy bookCopy, RentalState rentalState);

    List<BookRental> findAllByUser(User user);

    @Query("select br from BookRental br join fetch br.bookCopy where br.returnDate is null and br.rentalDate between :startDate and :endDate")
    List<BookRental> findAllRentalDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
