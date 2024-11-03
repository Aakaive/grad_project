package com.sparta.grad_project.domain.review.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewSaveResponse {
    private final Integer reviewStar ;
    private final String reviewTitle;
    private final String reviewDescription;
}
