package com.example.hyeop.web.dto;

import com.example.hyeop.domain.post.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsRequeestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsRequeestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
