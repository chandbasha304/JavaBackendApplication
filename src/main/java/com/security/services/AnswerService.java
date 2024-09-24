package com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Answer;
import com.entity.Question;
import com.entity.User;
import com.exceptions.AnswerAlreadyExistsException;
import com.exceptions.ResourceNotFoundException;
import com.repository.AnswerRepository;

import java.util.List;

@Service
public class AnswerService {
    
    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Answer not found with id: " + id));
    }

    public List<Answer> getAnswersByQuestion(Question question) {
        return answerRepository.findByQuestion(question);
    }

    public List<Answer> getAnswersByUser(User user) {
        return answerRepository.findByUser(user);
    }

    public Answer createAnswer(Answer answer) {
        if (answerRepository.findByQuestion(answer.getQuestion())
            .stream()
            .anyMatch(a -> a.getContent().equalsIgnoreCase(answer.getContent()) && a.getUser().equals(answer.getUser()))) {
            throw new AnswerAlreadyExistsException("You have already provided this answer for the question.");
        }
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        if (!answerRepository.existsById(answer.getId())) {
            throw new ResourceNotFoundException("Answer not found with id: " + answer.getId());
        }
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        if (!answerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Answer not found with id: " + id);
        }
        answerRepository.deleteById(id);
    }
}

