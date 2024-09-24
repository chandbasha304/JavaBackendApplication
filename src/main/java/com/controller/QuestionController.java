package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Question;
import com.security.services.QuestionService;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/getallquestions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/getquestionbyid/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/search")
    public List<Question> searchQuestions(@RequestParam String keyword) {
        return questionService.searchQuestions(keyword);
    }

    @PostMapping("/createquestion")
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question) {
        Question createdQuestion = questionService.createQuestion(question);
        return ResponseEntity.created(URI.create("/api/questions/" + createdQuestion.getId())).body(createdQuestion);
    }


    @PutMapping("/updatequestion/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        question.setId(id);
        Question updatedQuestion = questionService.updateQuestion(question);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/deletequestion/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}

