package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Answer;
import com.entity.Question;
import com.entity.User;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
    List<Answer> findByQuestion(Question question);
    
    List<Answer> findByUser(User user);
}

