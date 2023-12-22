package com.plusweek.domain.post.controller;

import com.plusweek.domain.post.dto.PostRequestDto;
import com.plusweek.domain.post.dto.PostResponseDto;
import com.plusweek.domain.post.repository.PostRepository;
import com.plusweek.domain.post.service.PostService;
import com.plusweek.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        List<PostResponseDto> postResponseDto = postService.getPosts(title, nickname, createdAt);
        return ResponseEntity.status(200).body(postResponseDto);
    }

    @GetMapping("/id")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        PostResponseDto postResponseDto = postService.getPost(id);
        return ResponseEntity.status(200).body(postResponseDto);
    }


    @PostMapping
    public ResponseEntity<String> addPost(@Valid @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.addPost(requestDto, userDetails.getUser());
        return ResponseEntity.status(200).body("게시물이 등록되었습니다");
    }

    @PatchMapping("/id")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updatePost(id, requestDto, userDetails.getUser());
        return ResponseEntity.status(200).body("게시글이 수정되었습니다");
    }
}
