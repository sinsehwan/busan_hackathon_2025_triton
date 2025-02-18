package com.example.triton.chatting.controller;

import com.example.triton.assist.AssistEntity;
import com.example.triton.assist.AssistService;
import com.example.triton.chatting.Entity.ChatMessage;
import com.example.triton.chatting.Entity.Chatting;
import com.example.triton.chatting.service.ChatService;
import com.example.triton.help.HelpEntity;
import com.example.triton.help.HelpService;
import com.example.triton.user.SiteUser;
import com.example.triton.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;

    /**
     * 채팅방 목록 페이지
     * @param model
     * @return
     */
    @Autowired
    private HelpService helpService;

    @Autowired
    private AssistService assistService;

    @GetMapping("/main/senior")
    public String chatMain(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();
        System.out.println(username);

        SiteUser user = userRepository.findByUsername(username);
        Long uid = user.getUId();
        System.out.println(uid);

        List<Chatting> chatList = chatService.findChatting(uid);
        System.out.println(chatList.size());

        model.addAttribute("uid", uid);

        model.addAttribute("chatList", chatList);

        List<SiteUser> volunteers = userRepository.findByUsertype("volunteer");
        model.addAttribute("volunteers", volunteers);

        List<HelpEntity> helps = helpService.getAllHelps();
        model.addAttribute("helps", helps);

        List<AssistEntity> assists = assistService.getAllAssists();
        model.addAttribute("assists", assists);

        return "main-senior";
    }

    @GetMapping("/main/volunteer")
    public String chatMainVolunteer(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();
        System.out.println(username);

        SiteUser user = userRepository.findByUsername(username);
        Long uid = user.getUId();
        System.out.println(uid);

        List<Chatting> chatList = chatService.findChatting(uid);
        System.out.println(chatList.size());

        model.addAttribute("uid", uid);

        model.addAttribute("chatList", chatList);

        List<SiteUser> volunteers = userRepository.findByUsertype("volunteer");
        model.addAttribute("volunteers", volunteers);

        List<HelpEntity> helps = helpService.getAllHelps();
        model.addAttribute("helps", helps);

        return "main-volunteer";
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

    @PostMapping("/{vid}/newChat/{agedId}")
    public String newChat(@PathVariable("vid") Long vid, @PathVariable("agedId") Long agedId){
        Chatting chatting = new Chatting();
        chatting.setVid(vid);
        chatting.setAgedid(agedId);
        SiteUser user1 = userRepository.findById(vid).get();
        SiteUser user2 = userRepository.findByUid(agedId).get();
        chatting.setChatName(user1 + ", " + user2);
        chatService.newChat(chatting);



        return "redirect:/main/senior";
    }
}
