package com.example.hyeop.web.dto;

import com.example.hyeop.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostModifyRequestDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String password;

    @Builder
    public PostModifyRequestDto(Long id, String title, String content, String author, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .password(password)
                .build();
    }
}
