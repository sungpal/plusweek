package com.plusweek.domain.comment.service;

import com.plusweek.domain.comment.dto.CommentRequestDto;
import com.plusweek.domain.comment.dto.CommentResponseDto;
import com.plusweek.domain.comment.entity.Comment;
import com.plusweek.domain.comment.repository.CommentRepository;
import com.plusweek.domain.post.entity.Post;
import com.plusweek.domain.post.repository.PostRepository;
import com.plusweek.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, User user) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다"));
        Comment comment = new Comment(requestDto, post, user);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

}
