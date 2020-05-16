package com.example.hyeop.service.posts;

import com.example.hyeop.domain.post.PostsRepository;
import com.example.hyeop.web.dto.PostsListResponseDto;
import com.example.hyeop.web.dto.PostsRequeestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.*;

@AllArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsRequeestDto requeestDto){
        return postsRepository.save(requeestDto.toEntity()).getId();
    }

    @Transactional
    public List<PostsListResponseDto> findAllByOrderByIdDesc(){
        return postsRepository.findAllByOrderByIdDesc().stream().map(PostsListResponseDto::new).collect(toList());
    }
}
