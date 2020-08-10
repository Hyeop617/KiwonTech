package com.example.hyeop.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MailRequestDto {
    private String title;
    private String content;
    private String author;
    private String email;

    @Builder
    public MailRequestDto(String title, String content, String author, String email) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.email = email;
    }
}
