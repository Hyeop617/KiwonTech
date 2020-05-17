package com.example.hyeop.web;

import com.example.hyeop.domain.post.Post;
import com.example.hyeop.service.post.PostService;
import com.example.hyeop.web.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bbs")
@Controller
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String bbs(Model model) {
        model.addAttribute("post", postService.findAllByOrderByIdDesc());
        return "bbs";
    }

    @GetMapping("/write")
    public String write(){
        return "bbs-write";
    }

    @GetMapping("/modifycheck/{id}")
    public String modifyCheck(@PathVariable String id, Model model){
        model.addAttribute("id", id);
        return "bbs-modify-check";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("post",postService.modify(id));
        return "bbs-modify";
    }
}
