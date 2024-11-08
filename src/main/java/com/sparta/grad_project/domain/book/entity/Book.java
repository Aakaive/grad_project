package com.sparta.grad_project.domain.book.entity;

import com.sparta.grad_project.domain.bookCopy.entity.BookCopy;
import com.sparta.grad_project.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String bookTitle;

    private Long bookPublished;

    @ElementCollection
    private List<String> authors = new ArrayList<>();

    @ElementCollection
    private List<String> publishers = new ArrayList<>();

    @ElementCollection
    private List<String> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<BookCopy> copyList = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Review> reviewList = new ArrayList<>();

    public Book(String isbn, String bookTitle, Long bookPublished, List<String> authors, List<String> publishers, List<String> subjects) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.bookPublished = bookPublished;
        if(!authors.isEmpty()) this.authors.addAll(authors);
        if(!publishers.isEmpty()) this.publishers.addAll(publishers);
        if(!subjects.isEmpty()) this.subjects.addAll(subjects);
    }

    public void update(String bookTitle, Long bookPublished) {
        if(bookTitle != null) this.bookTitle = bookTitle;
        if(bookPublished != null) this.bookPublished = bookPublished;
    }

    public void addAuthors(List<String> authors){
        this.authors.addAll(authors);
    }
    public void addPublishers(List<String> publishers){
        this.publishers.addAll(publishers);
    }
    public void addSubjects(List<String> subjects){
        this.subjects.addAll(subjects);
    }
}
