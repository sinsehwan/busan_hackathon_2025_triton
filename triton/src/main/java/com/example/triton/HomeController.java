package com.example.triton;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/welcome")
    public String showWelcome(){
        return "welcome.html";
    }
}
