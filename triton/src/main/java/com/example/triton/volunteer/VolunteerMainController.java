package com.example.triton.volunteer;

import com.example.triton.help.HelpEntity;
import com.example.triton.help.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VolunteerMainController {
    @Autowired
    private HelpService helpService;


    @GetMapping("/main/volunteer")
    public String seniorMain(Model model){
        List<HelpEntity> helps = helpService.getAllHelps();
        model.addAttribute("helps", helps);
        return "main-volunteer";
    }
}