package com.example.triton.senior;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeniorMainController {

    @GetMapping("/main/senior")
    public String seniorMain(){
        return "main-senior";
    }
}
