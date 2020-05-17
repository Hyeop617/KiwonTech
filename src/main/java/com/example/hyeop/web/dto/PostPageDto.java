package com.example.hyeop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostPageDto {
    private PageDto pageDto;
    private List<PostListResponseDto> postList;
}
