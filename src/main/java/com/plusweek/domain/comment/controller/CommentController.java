package com.plusweek.domain.comment.controller;

import com.plusweek.domain.comment.dto.CommentRequestDto;
import com.plusweek.domain.comment.service.CommentService;
import com.plusweek.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comment")
    public ResponseEntity<String> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.createComment(postId, requestDto, userDetails.getUser());
        return ResponseEntity.status(200).body("댓글이 작성되었습니다");
    }

}
