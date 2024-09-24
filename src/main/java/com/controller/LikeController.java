package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Answer;
import com.entity.Like;
import com.entity.User;
import com.security.services.AnswerService;
import com.security.services.LikeService;
import com.security.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;
    
    @Autowired
    private UserService userService;
    
    @Autowired 
    private AnswerService answerService;

    @GetMapping
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    @GetMapping("/by-user/{userId}")
    public List<Like> getLikesByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return likeService.getLikesByUser(user);
    }

    @GetMapping("/by-answer/{answerId}")
    public List<Like> getLikesByAnswer(@PathVariable Long answerId) {
        Answer answer = answerService.getAnswerById(answerId);
        return likeService.getLikesByAnswer(answer);
    }

    @PostMapping("/create like")
    public ResponseEntity<Like> createLike(@RequestBody Like like) {
        Like createdLike = likeService.createLike(like);
        return ResponseEntity.ok(createdLike);
    }

    @DeleteMapping("/deletelike/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }
}

