package com.example.hyeop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchPostDto {
    private String searchType;
    private String inSearch;
}
