package com.plusweek.domain.comment.service;

import com.plusweek.domain.comment.dto.CommentRequestDto;
import com.plusweek.domain.comment.dto.CommentResponseDto;
import com.plusweek.domain.comment.entity.Comment;
import com.plusweek.domain.comment.repository.CommentRepository;
import com.plusweek.domain.post.entity.Post;
import com.plusweek.domain.post.repository.PostRepository;
import com.plusweek.domain.user.entity.User;
import jakarta.transaction.Transactional;
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

    @Transactional
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("존재하지 않는 댓글입니다"));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new NullPointerException("댓글을 수정할 권한이 없습니다");
        }
        comment.setContent(requestDto.getContent());

        return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteComment(Long postId, Long commentId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("존재하지 않는 댓글입니다"));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new NullPointerException("댓글을 삭제할 권한이 없습니다");
        }
        commentRepository.delete(comment);
    }
}
