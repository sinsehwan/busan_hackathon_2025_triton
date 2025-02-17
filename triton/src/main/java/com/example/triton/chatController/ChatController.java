package com.example.triton.chatController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {


    /**
     * controller 수정 중
     * @param uId
     * @param model
     * @return
     */
    @GetMapping("/{uid}/messageInfo")
    public String messageInfo(@PathVariable int uId, Model model){
        return "index.html";
    }



}
