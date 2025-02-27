package com.example.triton.volunteer;

import com.example.triton.help.HelpEntity;
import com.example.triton.help.HelpService;
import com.example.triton.user.SiteUser;
import com.example.triton.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main/volunteer")
public class VolunteerMainController {
    @Autowired
    private HelpService helpService;
    private UserRepository userRepository;

}