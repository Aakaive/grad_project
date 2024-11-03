package com.sparta.grad_project.domain.review.dto.request;

import lombok.Getter;

@Getter
public class ReviewSaveRequest {
    private Integer reviewStar;
    private String reviewTitle;
    private String reviewDescription;
}
