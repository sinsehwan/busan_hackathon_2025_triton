package com.example.triton.assist;

import com.example.triton.chatting.Entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistRepository extends JpaRepository<AssistEntity, Long> {
}
