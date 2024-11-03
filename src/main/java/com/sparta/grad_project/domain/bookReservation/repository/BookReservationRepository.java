package com.sparta.grad_project.domain.bookReservation.repository;

import com.sparta.grad_project.domain.bookReservation.entity.BookReservation;
import com.sparta.grad_project.domain.bookReservation.entity.ReservatationState;
import com.sparta.grad_project.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
    List<BookReservation> findAllByUser(User user);

    List<BookReservation> findByReservationDateBeforeAndState(LocalDate threeDaysAgo, ReservatationState reservatationState);
}
