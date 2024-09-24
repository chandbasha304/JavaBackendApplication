package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Answer;
import com.entity.Comment;
import com.entity.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    List<Comment> findByUser(User user);
    
    List<Comment> findByAnswer(Answer answer);
}

