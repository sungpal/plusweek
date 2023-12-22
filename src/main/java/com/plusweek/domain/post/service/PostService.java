package com.plusweek.domain.post.service;

import com.plusweek.domain.post.dto.PostRequestDto;
import com.plusweek.domain.post.dto.PostResponseDto;
import com.plusweek.domain.post.entity.Post;
import com.plusweek.domain.post.repository.PostRepository;
import com.plusweek.domain.user.entity.User;
import com.plusweek.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public void addPost(PostRequestDto requestDto, User user) {

        Post post = new Post(requestDto, user);
        postRepository.save(post);
    }


    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다"));
        return new PostResponseDto(post);
    }

    public void updatePost(Long id, PostRequestDto requestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다"));
        if (!post.getUser().getNickname().equals(user.getNickname())) {
            throw new IllegalArgumentException("게시글을 수정할 권한이 없습니다");
        }
        post.update(requestDto);
    }
}
