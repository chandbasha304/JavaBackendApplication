package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Answer;
import com.entity.Question;
import com.entity.User;
import com.exceptions.ResourceNotFoundException;
import com.repository.AnswerRepository;
import com.repository.QuestionRepository;
import com.repository.UserRepository;
import com.security.services.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private AnswerRepository answerRepository;

    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/getanswerbyid/{id}")
    public Answer getAnswerById(@PathVariable Long id) {
        return answerService.getAnswerById(id);
    }

    @PostMapping("/createanser/answers")
    public ResponseEntity<?> createAnswer(
    	    @RequestParam("content") String content,
    	    @RequestParam("questionId") Long questionId,
    	    @RequestParam("userId") Long userId) {

    	    try {
    	        // Fetch Question and User entities
    	        Question question = questionRepository.findById(questionId)
    	            .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

    	        User user = userRepository.findById(userId)
    	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    	        // Create and set up Answer
    	        Answer answer = new Answer();
    	        answer.setContent(content);
    	        answer.setQuestion(question); // Set the valid Question entity
    	        answer.setUser(user); // Set the valid User entity

    	        // Save Answer
    	        answerRepository.save(answer);

    	        return ResponseEntity.ok(answer);
    	    } catch (ResourceNotFoundException e) {
    	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    	    } catch (Exception e) {
    	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    	    }
    	}

    @PutMapping("/updateanswer/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        answer.setId(id);
        Answer updatedAnswer = answerService.updateAnswer(answer);
        return ResponseEntity.ok(updatedAnswer);
    }

    @DeleteMapping("/deleteanswer/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}

