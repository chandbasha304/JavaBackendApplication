package com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Chat;
import com.entity.User;
import com.exceptions.ChatNotAllowedException;
import com.exceptions.ResourceNotFoundException;
import com.repository.ChatRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    
    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public List<Chat> getChatsBySender(User sender) {
        return chatRepository.findBySender(sender);
    }

    public List<Chat> getChatsByReceiver(User receiver) {
        return chatRepository.findByReceiver(receiver);
    }

    public List<Chat> getChatsBetweenUsers(User sender, User receiver) {
        return chatRepository.findBySenderAndReceiver(sender, receiver);
    }

    public Chat createChat(Chat chat) {
        if (!chat.getSender().isActive() || !chat.getReceiver().isActive()) {
            throw new ChatNotAllowedException("Chat is not allowed as one of the users is deactivated.");
        }
        chat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    public void deleteChat(Long id) {
        if (!chatRepository.existsById(id)) {
            throw new ResourceNotFoundException("Chat not found with id: " + id);
        }
        chatRepository.deleteById(id);
    }
}


