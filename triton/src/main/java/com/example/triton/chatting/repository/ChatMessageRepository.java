package com.example.triton.chatting.repository;

import com.example.triton.chatting.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatId(Long cid);
    Optional<ChatMessage> findByMid(Long mid);
    //
}
