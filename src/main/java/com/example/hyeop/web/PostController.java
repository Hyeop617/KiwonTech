package com.example.hyeop.web;

import com.example.hyeop.config.auth.dto.SessionUser;
import com.example.hyeop.domain.post.Post;
import com.example.hyeop.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/bbs")
@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("")
    public String bbs(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        model.addAttribute("postpage", postService.getPostPage(pageNum));
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if( user != null){
            model.addAttribute("username", user.getName());
        }
        return "bbs";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) throws Exception {
        Post post = postService.getPost(id);
        if(post == null){
            model.addAttribute("message", "해당 게시물이 없습니다.");
            return "error";
        }else {
            model.addAttribute("post", post);
            return "bbs-view";
        }

    }



    @GetMapping("/write")
    public String write(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if( user != null){
            model.addAttribute("username", user.getName());
        }
        return "bbs-write";
    }

    @GetMapping("/modifycheck/{id}")
    public String modifyCheck(@PathVariable String id, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(postService.checkRole(Long.valueOf(id))){
            if(user == null){
                model.addAttribute("message", "로그인이 필요합니다.");
                return "error";
            }
            else if(postService.checkPostUser(user.getName(), Long.valueOf(id))){
                model.addAttribute("username", user.getName());
                model.addAttribute("post", postService.getPost(Long.valueOf(id)));
                return "bbs-modify";
            }else{
                model.addAttribute("message", "해당 사용자가 아닙니다.");
                return "error";
            }
        }
            model.addAttribute("id", id);
            return "bbs-modify-check";
    }

    @GetMapping("/error")
    public String error(Model model){
        return "error";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("post", postService.getPost(id));
        return "bbs-modify";
    }

    @GetMapping("/search")
    public String search(@RequestParam("type") String type, @RequestParam("keyword") String keyword,Model model,@RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        model.addAttribute("postpage",postService.search(type, keyword,pageNum));
        return "bbs-search";
    }
}
