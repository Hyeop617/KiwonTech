package com.example.hyeop.web;

import com.example.hyeop.service.posts.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class PostsController {
    private final PostsService postsService;

    @GetMapping("/bbs")
    public String bbs(Model model) {
        model.addAttribute("posts", postsService.findAllByOrderByIdDesc());
        return "bbs";
    }

    @GetMapping("/bbs/write")
    public String write(){
        return "bbs-write";
    }
}
