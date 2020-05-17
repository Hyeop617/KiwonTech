package com.example.hyeop.domain.post;

import com.example.hyeop.web.dto.PostModifyRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    public Page<Post> findAllByTitleLike(String name, Pageable pageable);
    public Page<Post> findAllByContentLike(String name, Pageable pageable);

    public Page<Post> findAllByAuthorLike(String name, Pageable pageable);
}
