package com.example.triton.config;

import com.example.triton.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JudgeController {

    @Autowired
    private UserService userService;

    @GetMapping("/judge")
    public String judge(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String userType = userService.getUserTypeByUsername(user.getUsername()); // userType을 가져오는 로직

        // userType에 따라 리다이렉트 경로 설정
        if ("senior".equals(userType)) {
            return "redirect:/main/senior";
        } else if ("volunteer".equals(userType)) {
            return "redirect:/main/volunteer";
        } else {
            return "redirect:/welcome"; // 기본 경로
        }
    }
    // 실제로는 DB나 서비스에서 userType을 가져오는 로직이 필요합니
}
