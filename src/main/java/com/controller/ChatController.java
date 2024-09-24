package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Chat;
import com.entity.User;
import com.security.services.ChatService;
import com.security.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/between")
    public List<Chat> getChatsBetweenUsers(@RequestParam Long senderId, @RequestParam Long receiverId) {
        // Fetch sender and receiver using their IDs (userService can be used here)
        User sender = userService.getUserById(senderId);
        User receiver = userService.getUserById(receiverId);
        return chatService.getChatsBetweenUsers(sender, receiver);
    }

    @PostMapping("/createchat")
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) {
        Chat createdChat = chatService.createChat(chat);
        return ResponseEntity.ok(createdChat);
    }

    @DeleteMapping("/deletechat/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }
}
