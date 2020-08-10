package com.example.hyeop.service;

import com.example.hyeop.config.auth.dto.SessionUser;
import com.example.hyeop.domain.post.Post;
import com.example.hyeop.domain.post.PostRepository;
import com.example.hyeop.domain.user.Role;
import com.example.hyeop.domain.utils.PageUtil;
import com.example.hyeop.web.dto.*;
//import javafx.util.converter.IntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Session;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.*;

@Service
@Component
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Long save(SessionUser user, PostRequestDto requestDto) {
        Post post;
        if(user != null){
            Random random = new Random();
            post = Post.builder()
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .author(user.getName())
                    .password(passwordEncoder.encode(String.valueOf(random.nextInt())))
                    .role(Role.USER)
                    .build();
        }else{
            post = Post.builder()
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .author(requestDto.getAuthor())
                    .password(passwordEncoder.encode(requestDto.getPassword()))
                    .role(Role.GUEST)
                    .build();
        }
        return postRepository.save(post).getId();
    }

    public Long modifyPost(SessionUser user, PostModifyRequestDto requestDto) throws Exception {
        Post post = postRepository.findById(requestDto.getId()).orElseThrow(() -> new Exception("비밀번호가 틀립니다."));
        post.setContent(requestDto.getContent());
        post.setTitle(requestDto.getTitle());
        if(user != null){
            Random random = new Random();
            post.setAuthor(user.getName());
            post.setPassword(passwordEncoder.encode(String.valueOf(random.nextInt())));
        }else {
            post.setAuthor(requestDto.getAuthor());
            post.setPassword(requestDto.getPassword());
        }
        postRepository.save(post);
        return post.getId();

    }


    public PostPageDto getPostPage(Integer pageNum) {
        Page<Post> post = postRepository.findAll(PageRequest.of(pageNum - 1, 5, Sort.by(Sort.Direction.DESC, "id")));

        PageDto pageDto = PageDto.builder()
//                .postSize(post.getTotalElements())
//                .nowPageIndex(post.getNumber() + 1)
//                .pageSize(post.getTotalPages())
                .pageList(PageUtil.createPageList(1, post.getTotalPages()))
                .build();

        List<PostListResponseDto> list = post.getContent()
                .stream()
                .map(PostListResponseDto::new)
                .collect(toList());

        return PostPageDto.builder()
                .pageDto(pageDto)
                .postList(list)
                .build();
    }


    public Post findAvailablePost(Long id) throws Exception {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new Exception("계정이 없어요"));
    }


    public Long modifyCheck(PostModifyRequestDto requestDto) throws Exception {
        Post post = findAvailablePost(requestDto.getId());
        if(passwordEncoder.matches(requestDto.getPassword(),post.getPassword())){
                return post.getId();
                }
                return -1L;
                }

public Post getPost(Long id) throws Exception {
        Post post = postRepository.findById(id)
        .orElseThrow(() -> new Exception("비밀번호가 틀립니다."));
        return post;
        }

public void deletePost(PostModifyRequestDto requestDto) {
        postRepository.deleteById(requestDto.getId());
        }

public Boolean checkRole(Long id) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(() -> new Exception("포스트 없음"));
        return post.getRole().getKey().equals("ROLE_USER");
        }

public Boolean checkPostUser(String user, Long id) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(() -> new Exception("포스트 없음"));
        return post.getAuthor().equals(user);
        }
public PostPageDto search(String type, String keyword, Integer pageNum) {
        Page<Post> post;


        if(type.equals("title")){
        post = postRepository.findAllByTitleLike("%"+keyword+"%",PageRequest.of(pageNum - 1, 5, Sort.by(Sort.Direction.DESC, "id")));
        }else if (type.equals("author")){
        post = postRepository.findAllByAuthorLike("%"+keyword+"%",PageRequest.of(pageNum - 1, 5, Sort.by(Sort.Direction.DESC, "id")));
        }else{
        post = postRepository.findAllByContentLike("%"+keyword+"%",PageRequest.of(pageNum - 1, 5, Sort.by(Sort.Direction.DESC, "id")));
        }

        PageDto pageDto = PageDto.builder()
        .postSize(post.getTotalElements())
        .nowPageIndex(post.getNumber() + 1)
        .pageSize(post.getTotalPages())
        .pageList(PageUtil.createPageList(1, post.getTotalPages()))
        .build();

        List<PostListResponseDto> list = post.getContent()
        .stream()
        .map(PostListResponseDto::new)
        .collect(toList());

        return PostPageDto.builder()
        .pageDto(pageDto)
        .postList(list)
        .build();
        }

public void sendMail(MailRequestDto requestDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("totoll13mail@gmail.com");
        message.setTo(requestDto.getEmail());
        message.setSubject("게시판에서 보낸 메일 : " +requestDto.getTitle());
        message.setText(requestDto.getContent());
        javaMailSender.send(message);
        }
        }
