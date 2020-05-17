package com.example.hyeop.service.post;

import com.example.hyeop.domain.post.Post;
import com.example.hyeop.domain.post.PostRepository;
import com.example.hyeop.domain.utils.PageUtil;
import com.example.hyeop.domain.utils.PasswordUtil;
import com.example.hyeop.web.dto.*;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@Component
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public Long save(PostRequestDto requestDto) {
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
        if (PasswordUtil.isAvailPassword(post.getPassword(), requestDto.getPassword())) {
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
}
