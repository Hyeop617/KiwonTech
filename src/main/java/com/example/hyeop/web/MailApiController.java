package com.example.hyeop.web;

import com.example.hyeop.service.PostService;
import com.example.hyeop.web.dto.MailRequestDto;
import com.example.hyeop.web.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MailApiController {

    private final PostService postService;

    @PostMapping("/api/sendmail")
    public void sendMail(@RequestBody MailRequestDto requestDto){
        postService.sendMail(requestDto);
    }
}
