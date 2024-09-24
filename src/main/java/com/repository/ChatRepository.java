package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Chat;
import com.entity.User;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    
    List<Chat> findBySender(User sender);
    
    List<Chat> findByReceiver(User receiver);
    
    List<Chat> findBySenderAndReceiver(User sender, User receiver);
}
