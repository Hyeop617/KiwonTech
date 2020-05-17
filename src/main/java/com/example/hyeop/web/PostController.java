package com.example.hyeop.web;

import com.example.hyeop.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bbs")
@Controller
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("")
    public String bbs(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        model.addAttribute("postpage", postService.getPostPage(pageNum));
        return "bbs";

    }

    @GetMapping("/write")
    public String write() {
        return "bbs-write";
    }

    @GetMapping("/modifycheck/{id}")
    public String modifyCheck(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "bbs-modify-check";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("post", postService.modify(id));

        return "bbs-modify";
    }

    @GetMapping("/search")
    public String search(@RequestParam("type") String type, @RequestParam("keyword") String keyword,Model model,@RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        model.addAttribute("postpage",postService.search(type, keyword,pageNum));
        return "bbs-search";
    }
}
