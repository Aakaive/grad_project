package com.sparta.grad_project.domain.review.entity;

import com.sparta.grad_project.common.entity.Timestamped;
import com.sparta.grad_project.domain.book.entity.Book;
import com.sparta.grad_project.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "review")
public class Review extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer reviewStar;
    @Column(nullable = false)
    private String reviewTitle;
    @Column(nullable = false)
    private String reviewDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Review(Integer reviewStar, String reviewTitle, String reviewDescription, Book book, User user) {
        this.reviewStar = reviewStar;
        this.reviewTitle = reviewTitle;
        this.reviewDescription = reviewDescription;
        this.book = book;
        this.user = user;
    }

    public void update(Integer reviewStar, String reviewTitle, String reviewDescription) {
        this.reviewStar = reviewStar;
        this.reviewTitle = reviewTitle;
        this.reviewDescription = reviewDescription;
    }
}



