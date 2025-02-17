package com.example.triton.chatController.service;

import com.example.triton.chatController.Entity.ChatMessage;
import com.example.triton.chatController.Entity.Chatting;
import com.example.triton.chatController.repository.ChatMessageRepository;
import com.example.triton.chatController.repository.ChatRepository;
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

    /**
     * 참여중인 채팅 목록 가져오기
     */
    public List<Chatting> findChatting(int uId) {
        List<Chatting> chatList = chatRepository.findByVIdOrAgedId(uId, uId);
        return new ArrayList<>(chatList);
    }

    /**
     * 선택된 채팅에 저장된 메시지들 반환
     * @param cId
     * @return
     */
    public List<ChatMessage> getMessages(int cId){
        List<ChatMessage> messages = messageRepository.findByCId(cId);
        return new ArrayList<>(messages);
    }

    /**
     * 메시지 보내기
     * @param chatMessage
     * @return
     */
    public ChatMessage sendMessage(ChatMessage chatMessage){
        ChatMessage saved = messageRepository.save(chatMessage);
        return saved;
    }

    /**
     * 새 채팅 생성(매칭될 시 자동으로 생성되도록.)
     * @param chatting
     * @return
     */
    public Chatting newChat(Chatting chatting){
        Chatting saved = chatRepository.save(chatting);
        return saved;
    }

    /**
     * 메시지 삭제.(수정중)
     */
    public void deleteMessage(int mId){
        ChatMessage targetMessage = messageRepository.findByMId(mId);
        messageRepository.delete(targetMessage);
    }

    /**
     * (수정중)
     */
    public void deleteChat(int cId){

    }
}
