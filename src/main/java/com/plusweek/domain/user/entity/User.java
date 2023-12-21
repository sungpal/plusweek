package com.plusweek.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname; //  ID 및 닉네임
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String nickname, String password, UserRoleEnum role) {
        this.nickname = nickname;
        this.password = password;
        this.role = role;

    }
}
