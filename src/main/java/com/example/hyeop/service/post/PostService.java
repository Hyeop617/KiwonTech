package com.example.hyeop.service.post;

import com.example.hyeop.domain.post.Post;
import com.example.hyeop.domain.post.PostRepository;
import com.example.hyeop.domain.utils.PasswordUtil;
import com.example.hyeop.web.dto.PostListResponseDto;
import com.example.hyeop.web.dto.PostModifyRequestDto;
import com.example.hyeop.web.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

@Service
@Component
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public Long save(PostRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).getId();
    }

    public Long modifyPost(PostModifyRequestDto requestDto) throws Exception {
        Post post = postRepository.findById(requestDto.getId())
                .orElseThrow(() -> new Exception("없어요."));
        post.setAuthor(requestDto.getAuthor());
        post.setContent(requestDto.getContent());
        post.setPassword(requestDto.getPassword());
        post.setTitle(requestDto.getTitle());
        postRepository.save(post);
        return post.getId();

    }


    public List<PostListResponseDto> findAllByOrderByIdDesc(){
        return postRepository.findAllByOrderByIdDesc()
                .stream()
                .map(PostListResponseDto::new)
                .collect(toList());

    }


    public Post findAvailablePost(Long id) throws Exception {
        return postRepository
                .findById(id)
                .orElseThrow(()-> new Exception("계정이 없어요"));
    }



    public Long modifyCheck(PostModifyRequestDto requestDto) throws Exception {
        Post post = findAvailablePost(requestDto.getId());
        if(PasswordUtil.isAvailPassword(post.getPassword(), requestDto.getPassword())){
            return post.getId();
        }
        return -1L;
    }

    public Post modify(Long id) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception("없어요."));
        return post;
    }

    public void deletePost(PostModifyRequestDto requestDto) {
        postRepository.deleteById(requestDto.getId());
    }
}
