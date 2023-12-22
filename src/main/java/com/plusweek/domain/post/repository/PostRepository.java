package com.plusweek.domain.post.repository;

import com.plusweek.domain.post.entity.Post;
import com.plusweek.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);

    List<Post> findByTitleAndNicknameOrderByCreatedAtDesc(String title, String nickname, LocalDateTime createdAt);
}
