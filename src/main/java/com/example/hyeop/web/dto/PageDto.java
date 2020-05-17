package com.example.hyeop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PageDto {
    private Integer pageSize;
    private Integer firstPageIndex;
    private Integer nowPageIndex;
    private Integer lastPageIndex;
    private List<Integer> pageList;
    private long postSize;
}
