package com.example.hyeop.web;

import com.example.hyeop.service.posts.PostsService;
import com.example.hyeop.web.dto.PostsRequeestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/save")
    public Long save(@RequestBody PostsRequeestDto requestDto) {
        return postsService.save(requestDto);

    }
}
