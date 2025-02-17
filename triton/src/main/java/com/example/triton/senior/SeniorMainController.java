package com.example.triton.senior;

import com.example.triton.help.HelpEntity;
import com.example.triton.help.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SeniorMainController {
    @Autowired
    private HelpService helpService;

}
