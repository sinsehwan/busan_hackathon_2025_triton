package com.example.triton.gemini;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatTest {
    @GetMapping("/chat")
    public String showChat(){
        return "chat";
    }
}
