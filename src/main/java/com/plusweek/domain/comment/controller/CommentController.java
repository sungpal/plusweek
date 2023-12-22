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
@RequestMapping("/posts/{postId}/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.createComment(postId, requestDto, userDetails.getUser());
        return ResponseEntity.status(201).body("댓글이 작성되었습니다");
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.updateComment(postId,commentId, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body("댓글이 수정되었습니다");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(postId,commentId, userDetails.getUser());
        return ResponseEntity.ok().body("댓글 삭제가 완료되었습니다");
    }
}
