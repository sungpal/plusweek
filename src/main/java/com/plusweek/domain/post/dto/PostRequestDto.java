package com.plusweek.domain.post.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class PostRequestDto {
    @Length(max = 500)
    private String title;

    @Length(max = 5000)
    private String content;

    public PostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}