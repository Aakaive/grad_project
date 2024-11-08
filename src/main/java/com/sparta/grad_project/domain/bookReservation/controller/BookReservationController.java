package com.sparta.grad_project.domain.bookReservation.controller;

import com.sparta.grad_project.domain.bookReservation.dto.BookReservationRequestDto;
import com.sparta.grad_project.domain.bookReservation.dto.BookReservationResponseDto;
import com.sparta.grad_project.domain.bookReservation.service.BookReservationService;
import com.sparta.grad_project.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookReservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;

    // 책 대여 예약은 예약일자로부터 3일 이내에 대여해야 함.
    @PostMapping
    public ResponseEntity<BookReservationResponseDto> submitBookReservation(@RequestBody BookReservationRequestDto bookReservationRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BookReservationResponseDto bookReservationResponseDto = bookReservationService.submitBookReservation(bookReservationRequestDto, userDetails);

        return ResponseEntity.ok(bookReservationResponseDto);
    }

    @DeleteMapping("/{bookReservationId}")
    public ResponseEntity<Long> deleteBookReservation(@PathVariable("bookReservationId") Long bookReservationId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long deletedBookReservationId =  bookReservationService.deleteBookReservation(bookReservationId, userDetails);
        return ResponseEntity.ok(deletedBookReservationId);
    }

    @GetMapping()
    public ResponseEntity<List<BookReservationResponseDto>> getBookReservations(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<BookReservationResponseDto> bookReservationResponseDtoList = bookReservationService.getBookReservations(userDetails);

        return ResponseEntity.ok(bookReservationResponseDtoList);
    }

    @GetMapping("/me")
    public ResponseEntity<List<BookReservationResponseDto>> getBookReservationsByUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<BookReservationResponseDto> bookReservationResponseDtoList = bookReservationService.getBookReservationsByUser(userDetails);
        return ResponseEntity.ok(bookReservationResponseDtoList);
    }
}
