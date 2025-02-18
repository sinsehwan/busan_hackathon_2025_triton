package com.example.triton.chatting.controller;

import com.example.triton.chatting.Entity.Chatting;
import com.example.triton.chatting.repository.ChatRepository;
import com.example.triton.user.SiteUser;
import com.example.triton.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ConnectionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;


    @GetMapping("/connection/volunteer")
    public String connectionPage(@RequestParam String username, Model model) {
        model.addAttribute("username", username);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String volunteerUsername = ((UserDetails) principal).getUsername();

        SiteUser user1 = userRepository.findByUsername(volunteerUsername);
        Long uid1 = user1.getUId();

        SiteUser user2 = userRepository.findByUsername(username);
        Long uid2 = user2.getUId();

        Chatting chatting = new Chatting();

        chatting.setVid(uid1);
        chatting.setAgedid(uid2);
        chatting.setChatName(volunteerUsername + ", " + username);

        chatRepository.save(chatting);

        return "redirect:/main/volunteer"; // connection.html로 이동
    }

    @GetMapping("/connection/senior")
    public String connectionPageVol(@RequestParam String username, Model model) {
        model.addAttribute("username", username);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String seniorUsername = ((UserDetails) principal).getUsername();

        SiteUser user1 = userRepository.findByUsername(username);
        Long uid1 = user1.getUId();

        SiteUser user2 = userRepository.findByUsername(seniorUsername);
        Long uid2 = user2.getUId();

        Chatting chatting = new Chatting();

        chatting.setVid(uid1);
        chatting.setAgedid(uid2);
        chatting.setChatName(username + ", " + seniorUsername);

        chatRepository.save(chatting);

        return "redirect:/main/senior"; // connection.html로 이동
    }
}
