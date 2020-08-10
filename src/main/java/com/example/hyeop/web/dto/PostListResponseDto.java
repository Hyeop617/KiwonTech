package com.example.hyeop.web.dto;

import com.example.hyeop.domain.post.Post;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modify_date;

    public PostListResponseDto(Post entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modify_date = entity.getModify_date();
    }

}
