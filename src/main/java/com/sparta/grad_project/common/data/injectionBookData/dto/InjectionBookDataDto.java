package com.sparta.grad_project.common.data.injectionBookData.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class InjectionBookDataDto {
    private String isbn;
    private String bookTitle;
    private Long bookPublished;
    private List<String> authors;
    private List<String> publishers;
    private List<String> subjects;
}
