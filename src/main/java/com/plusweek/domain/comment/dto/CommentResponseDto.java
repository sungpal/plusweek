package com.plusweek.domain.comment.dto;

import com.plusweek.domain.comment.entity.Comment;

import java.time.LocalDateTime;

public class CommentResponseDto {
    String content;
    String nickname;
    LocalDateTime createdAt;


    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.nickname = comment.getUser().getNickname();
        this.createdAt = comment.getCreatedAt();
    }
}
