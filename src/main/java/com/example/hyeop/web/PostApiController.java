package com.example.hyeop.web;

import com.example.hyeop.domain.post.Post;
import com.example.hyeop.service.post.PostService;
import com.example.hyeop.web.dto.PostModifyRequestDto;
import com.example.hyeop.web.dto.PostRequestDto;
import com.example.hyeop.web.dto.PostResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/save")
    public Long save(@RequestBody PostRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PostMapping("/api/modifycheck")
    public Long modifyCheck(@RequestBody PostModifyRequestDto requestDto) throws Exception {
        return postService.modifyCheck(requestDto);
    }

    @PostMapping("/api/modify")
    public Long modify(@RequestBody PostModifyRequestDto requestDto) throws Exception {
        return postService.modifyPost(requestDto);
    }

    @PostMapping("/api/delete")
    public void delete(@RequestBody PostModifyRequestDto requestDto) throws Exception {
        postService.deletePost(requestDto);
    }
}
