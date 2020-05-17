package com.example.hyeop.web.dto;

import com.example.hyeop.domain.post.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
//    private String password;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
//        this.password = entity.getPassword();
    }
}
