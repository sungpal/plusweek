package com.plusweek.domain.user.dto;

import com.plusweek.domain.user.entity.User;

public class UserResponseDto {
    String nickname;

    public UserResponseDto(User user) {
        this.nickname = user.getNickname();
    }
}
