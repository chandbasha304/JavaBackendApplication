package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Answer;
import com.entity.Like;
import com.entity.User;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
    List<Like> findByUser(User user);
    
    List<Like> findByAnswer(Answer answer);
}

