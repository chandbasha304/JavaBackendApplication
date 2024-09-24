package com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Answer;
import com.entity.Comment;
import com.entity.User;
import com.exceptions.ResourceNotFoundException;
import com.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
    }

    public List<Comment> getCommentsByUser(User user) {
        return commentRepository.findByUser(user);
    }

    public List<Comment> getCommentsByAnswer(Answer answer) {
        return commentRepository.findByAnswer(answer);
    }

    public Comment createComment(Comment comment) {
        comment.setTimestamp(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        if (!commentRepository.existsById(comment.getId())) {
            throw new ResourceNotFoundException("Comment not found with id: " + comment.getId());
        }
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
}


