package com.sparta.grad_project.domain.review.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReviewsGetResponse {
    private final Long bookId;
    private final Long reviewId;
    private final Integer reviewStar;
    private final String reviewTitle;
    private final String reviewDescription;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;
}
