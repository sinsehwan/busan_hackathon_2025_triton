package com.example.triton.chatting.controller;

import com.example.triton.chatting.Entity.ChatMessage;
import com.example.triton.chatting.Entity.Chatting;
import com.example.triton.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * 채팅방 목록 페이지
     * @param uid
     * @param model
     * @return
     */
    @GetMapping("/{uid}/chat/list")
    public String chatMain(@PathVariable("uid") Long uid, Model model){
        List<Chatting> chatList = chatService.findChatting(uid);
        System.out.println(chatList.size());

        model.addAttribute("chatList", chatList);
        return "main-senior";
    }

    /**
     * 클라이언트로부터 받는 메시지 처리 메소드
     * @param cid
     * @param message
     */
    @MessageMapping("/{cid}/sendMessage/{senderId}")
    public void sendMessage(@DestinationVariable("cid") Long cid, @DestinationVariable("senderId") Long senderId, String message){
        String destination = "/topic/messages/" + cid;
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage(message);
        chatMessage.setChatId(cid);
        chatMessage.setSenderId(senderId);
        chatMessage.setMtime(LocalDateTime.now());
        // 메시지 broadcast 위해 전송.
        messagingTemplate.convertAndSend(destination, chatMessage);
        //DB에 메시지 추가
        ChatMessage result = chatService.saveMessage(chatMessage);
        System.out.println(result.getChatId());
    }

    /**
     * 채팅 화면
     * @param uid
     * @param cid
     * @param model
     * @return
     */
    @GetMapping("/{uid}/chatting/{cid}")
    public String chatting(@PathVariable("uid") Long uid, @PathVariable("cid") Long cid, Model model){
        List<ChatMessage> existingMes = chatService.getMessages(cid);
        model.addAttribute("existingMes", existingMes);
        model.addAttribute("uid", uid);
        return "chat_test/chatting_draft";
    }
}
