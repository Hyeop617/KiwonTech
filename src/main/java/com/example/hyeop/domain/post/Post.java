package com.example.hyeop.domain.post;

import com.example.hyeop.domain.TimeEntity;
import com.example.hyeop.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String author;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Post(String title, String content, String author, String password, Role role) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
        this.role = role;
    }
}
