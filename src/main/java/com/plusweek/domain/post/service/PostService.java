package com.plusweek.domain.post.service;

import com.plusweek.domain.post.dto.PostRequestDto;
import com.plusweek.domain.post.dto.PostResponseDto;
import com.plusweek.domain.post.entity.Post;
import com.plusweek.domain.post.repository.PostRepository;
import com.plusweek.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
    public class PostService {

    private final PostRepository postRepository;

    public List<PostResponseDto> getPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        return posts.stream()
                .map(post -> new PostResponseDto(post.getUser().getNickname(), post.getCreatedAt(), post.getTitle()))
                .collect(Collectors.toList());
    }


    public void addPost(PostRequestDto requestDto, User user) {

        Post post = new Post(requestDto, user);
        postRepository.save(post);
    }


    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다"));
        return new PostResponseDto(post);
    }

    @Transactional
    public void updatePost(Long id, PostRequestDto requestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다"));
        if (!post.getUser().getNickname().equals(user.getNickname())) {
            throw new IllegalArgumentException("게시글을 수정할 권한이 없습니다");
        }
        post.update(requestDto);
    }

    @Transactional
    public void deletePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다"));
        if (!post.getUser().getNickname().equals(user.getNickname())) {
            throw new IllegalArgumentException("게시글을 삭제할 권한이 없습니다");
        }
        postRepository.delete(post);
    }
}
