package com.plusweek.domain.user.entity;

import com.plusweek.domain.post.entity.Post;
import com.plusweek.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public User(String nickname, String password, UserRoleEnum role) {

        this.nickname = nickname;
        this.password = password;
        this.role = role;

    }
}
