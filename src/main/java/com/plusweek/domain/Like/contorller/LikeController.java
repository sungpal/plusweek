package com.plusweek.domain.Like.contorller;

import com.plusweek.domain.Like.service.LikeService;
import com.plusweek.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> like(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.like(postId, userDetails.getUser().getId());
        return ResponseEntity.status(200).body("좋아요");
    }

    @DeleteMapping("/{postId}/like")
    public ResponseEntity<String> disLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.dislike(postId, userDetails.getUser().getId());
        return ResponseEntity.status(200).body("좋아요 취소");
    }
}
