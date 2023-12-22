package com.plusweek.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plusweek.domain.comment.dto.CommentResponseDto;
import com.plusweek.domain.comment.entity.Comment;
import com.plusweek.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String nickName;

    @JsonFormat( pattern = "yyyy-MM-dd" )
    private LocalDateTime createdAt;
    @JsonFormat( pattern = "yyyy-MM-dd" )
    private LocalDateTime modifiedAt;

    private String title;
    private String content;
    private List<CommentResponseDto> comments;


    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickName = post.getUser().getNickname();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.comments = post.getComments()
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

}