package com.controller;

import com.entity.Answer;
import com.entity.Comment;
import com.entity.User;
import com.exceptions.ResourceNotFoundException;
import com.security.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/getcommentbyid/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable Long userId) {
        User user = new User(); // Assuming a User object is needed; replace with actual user fetching logic
        user.setId(userId);
        List<Comment> comments = commentService.getCommentsByUser(user);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/answer/{answerId}")
    public ResponseEntity<List<Comment>> getCommentsByAnswer(@PathVariable Long answerId) {
        Answer answer = new Answer(); // Assuming an Answer object is needed; replace with actual answer fetching logic
        answer.setId(answerId);
        List<Comment> comments = commentService.getCommentsByAnswer(answer);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/createcomment")
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.ok(createdComment);
    }

    @PutMapping("/updatecomment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @Valid @RequestBody Comment commentDetails) {
        commentDetails.setId(id); // Ensure the ID is set in the details object
        Comment updatedComment = commentService.updateComment(commentDetails);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/deletecomment/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
