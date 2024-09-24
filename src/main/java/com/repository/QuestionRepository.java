package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Question;
import com.entity.User;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    List<Question> findByTitleContainingOrDescriptionContaining(String title, String description);
    
    List<Question> findByUser(User user);
    
    List<Question> findByActive(boolean active);
}
