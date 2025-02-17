package com.example.triton.chatting.service;

import com.example.triton.chatting.Entity.ChatMessage;
import com.example.triton.chatting.Entity.Chatting;
import com.example.triton.chatting.repository.ChatMessageRepository;
import com.example.triton.chatting.repository.ChatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatMessageRepository messageRepository;


    public List<Chatting> findChatting(Long uid) {
        List<Chatting> findChat = chatRepository.findByVidOrAgedid(uid, uid);
        return new ArrayList<>();
    }


    public List<ChatMessage> getMessages(Long cid){
        List<ChatMessage> messages = messageRepository.findByChatId(cid);
        return new ArrayList<>(messages);
    }


    public ChatMessage saveMessage(ChatMessage chatMessage){
        ChatMessage saved = messageRepository.save(chatMessage);
        return saved;
    }


    public Chatting newChat(Chatting chatting){
        Chatting saved = chatRepository.save(chatting);
        return saved;
    }

    public void deleteMessage(Long mid){
        ChatMessage targetMessage = messageRepository.findByMid(mid).get();
        messageRepository.delete(targetMessage);
    }


    public void deleteChat(Long cid){
        Chatting targetChat = chatRepository.findByCid(cid).get();
        chatRepository.delete(targetChat);
    }
}


