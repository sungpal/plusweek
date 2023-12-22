package com.plusweek.domain.Like.repository;

import com.plusweek.domain.Like.entity.Like;
import com.plusweek.domain.post.entity.Post;
import com.plusweek.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
}
