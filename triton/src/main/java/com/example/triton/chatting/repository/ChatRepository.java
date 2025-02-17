package com.example.triton.chatting.repository;


import com.example.triton.chatting.Entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chatting, Long> {
    List<Chatting> findByVidOrAgedid(Long vid, Long agedid);
    List<Chatting> findByVid(Long vid);
    List<Chatting> findByAgedid(Long agedid);
    Optional<Chatting> findByCid(Long cid);
}
