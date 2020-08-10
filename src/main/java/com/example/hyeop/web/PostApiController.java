package com.example.hyeop.web;

import com.example.hyeop.config.auth.dto.SessionUser;
import com.example.hyeop.domain.post.Post;
import com.example.hyeop.domain.user.User;
import com.example.hyeop.service.PostService;
import com.example.hyeop.web.dto.PostModifyRequestDto;
import com.example.hyeop.web.dto.PostRequestDto;
import com.example.hyeop.web.dto.PostResponseDto;
import com.example.hyeop.web.dto.SearchPostDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@AllArgsConstructor
@RestController
@Slf4j
public class PostApiController {

    private final PostService postService;

    private HttpSession httpSession;

    @PostMapping("/api/save")
    public Long save(@RequestBody PostRequestDto requestDto) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return postService.save(user, requestDto);
    }

    @PostMapping("/api/modifycheck")
    public Long modifyCheck(@RequestBody PostModifyRequestDto requestDto) throws Exception {
        return postService.modifyCheck(requestDto);
    }

    @PostMapping("/api/modify")
    public Long modify(@RequestBody PostModifyRequestDto requestDto) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return postService.modifyPost(user,requestDto);
    }




}
