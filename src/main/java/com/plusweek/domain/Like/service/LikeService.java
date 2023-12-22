package com.plusweek.domain.Like.service;

import com.plusweek.domain.Like.entity.Like;
import com.plusweek.domain.Like.repository.LikeRepository;
import com.plusweek.domain.post.entity.Post;
import com.plusweek.domain.post.repository.PostRepository;
import com.plusweek.domain.user.entity.User;
import com.plusweek.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;


    public Long like(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("존재하지 않는 사용자입니다"));

        Optional<Like> like = likeRepository.findByUserAndPost(user, post);
        if (like.isPresent()) {
            throw new IllegalArgumentException("이미 좋아요를 누르신 게시글입니다");
        }
        return likeRepository.save(Like.builder()
                .post(post)
                .user(user)
                .build()).
                getId();
    }

    public void dislike(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("존재하지 않는 사용자입니다"));

        Optional<Like> like = likeRepository.findByUserAndPost(user, post);
        if (like.isEmpty()) {
            throw new IllegalArgumentException("좋아요를 누른 내역이 없습니다");
        }
        likeRepository.deleteById(like.get().getId());
    }
}

