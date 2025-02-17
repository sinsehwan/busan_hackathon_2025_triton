package com.example.triton.chatController.repository;

import com.example.triton.chatController.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

    List<ChatMessage> findByCId(Integer cId);
    ChatMessage findByMId(Integer mId);
}
