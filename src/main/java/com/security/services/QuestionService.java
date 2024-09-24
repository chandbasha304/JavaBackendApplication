package com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Answer;
import com.entity.Question;
import com.entity.User;
import com.exceptions.ResourceNotFoundException;
import com.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));
    }

    public List<Question> searchQuestions(String keyword) {
        return questionRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }

    public List<Question> getQuestionsByUser(User user) {
        return questionRepository.findByUser(user);
    }

    public Question createQuestion(Question question) {
        List<Answer> answers = question.getAnswers();
        if (answers != null) {
            for (Answer answer : answers) {
                answer.setQuestion(question);
            }
        }
        return questionRepository.save(question);
    }


    public Question updateQuestion(Question question) {
        if (!questionRepository.existsById(question.getId())) {
            throw new ResourceNotFoundException("Question not found with id: " + question.getId());
        }
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id);
        }
        questionRepository.deleteById(id);
    }
}
