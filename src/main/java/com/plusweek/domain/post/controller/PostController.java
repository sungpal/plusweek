package com.plusweek.domain.post.controller;

import com.plusweek.domain.post.dto.PostResponseDto;
import com.plusweek.domain.post.repository.PostRepository;
import com.plusweek.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts(String title, String nickname, LocalDateTime createdAt) {
        List<PostResponseDto> postResponseDto = postService.getPosts(title, nickname,createdAt);
        return ResponseEntity.status(200).body(postResponseDto);
    }

}
