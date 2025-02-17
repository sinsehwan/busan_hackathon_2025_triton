package com.example.triton.chatController.repository;

import com.example.triton.chatController.Entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chatting, Integer> {
    List<Chatting> findByVIdOrAgedId(Integer vId, Integer agedId);
}