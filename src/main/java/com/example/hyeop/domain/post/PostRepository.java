package com.example.hyeop.domain.post;

import com.example.hyeop.web.dto.PostModifyRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc();
}
