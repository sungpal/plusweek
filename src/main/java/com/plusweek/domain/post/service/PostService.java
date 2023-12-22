package com.plusweek.domain.post.service;

import com.plusweek.domain.post.dto.PostResponseDto;
import com.plusweek.domain.post.entity.Post;
import com.plusweek.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponseDto> getPosts(String title, String nickname, LocalDateTime createdAt) {
        List<Post> posts = postRepository.findByTitleAndNicknameOrderByCreatedAtDesc(title, nickname, createdAt);
        return posts.stream().map(PostResponseDto::new).toList();

    }
}
