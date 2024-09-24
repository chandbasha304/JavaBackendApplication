package com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Answer;
import com.entity.Like;
import com.entity.User;
import com.exceptions.InvalidOperationException;
import com.exceptions.ResourceNotFoundException;
import com.repository.LikeRepository;

import java.util.List;

@Service
public class LikeService {
    
    @Autowired
    private LikeRepository likeRepository;

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public List<Like> getLikesByUser(User user) {
        return likeRepository.findByUser(user);
    }

    public List<Like> getLikesByAnswer(Answer answer) {
        return likeRepository.findByAnswer(answer);
    }

    public Like createLike(Like like) {
        if (likeRepository.findByAnswer(like.getAnswer())
            .stream()
            .anyMatch(l -> l.getUser().equals(like.getUser()))) {
            throw new InvalidOperationException("You have already liked this answer.");
        }
        return likeRepository.save(like);
    }

    public void deleteLike(Long id) {
        if (!likeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Like not found with id: " + id);
        }
        likeRepository.deleteById(id);
    }
}


