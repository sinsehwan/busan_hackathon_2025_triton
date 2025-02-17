package com.example.triton.chatting.controller;

import com.example.triton.chatting.Entity.ChatMessage;
import com.example.triton.chatting.Entity.Chatting;
import com.example.triton.chatting.repository.ChatMessageRepository;
import com.example.triton.chatting.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageRepository messageRepository;
    private final ChatRepository chatRepository;

    private final SimpMessagingTemplate messagingTemplate;


    @GetMapping("/{uid}/chat/list")
    public String chatMain(@PathVariable("uid") Long uid, Model model){
        List<Chatting> chatList = chatRepository.findByVidOrAgedid(uid, uid);

        model.addAttribute("chatList", chatList);
        return "chat_list";
    }

    @MessageMapping("/{cid}/sendMessage") // 클라이언트가 여기로 메시지 보내면 이 메소드 호출됨.
    public void sendMessage(@DestinationVariable("cid") Long cid, String message){
        String destination = "/topic/messages/" + cid;
        messagingTemplate.convertAndSend(destination, message);
        System.out.println(message + destination);
    }

    @GetMapping("/{uid}/chatting/{cid}")
    public String chatting(@PathVariable("uid") Long uid, @PathVariable("cid") Long cid, Model model){
        List<ChatMessage> existingMes = messageRepository.findByCid(cid);
        model.addAttribute("existingMes", existingMes);
        return "chat_test/chatting";
    }
}
