package com.example.triton.user;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.dao.DataIntegrityViolationException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserVolunteerController {

    private final UserService userService;

    @GetMapping("/signup-volunteer")
    public String signup(UserCreateForm userCreateForm) {
        return "signup-volunteer";
    }

    @PostMapping("/signup-volunteer")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup-volunteer";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup-volunteer";
        }

        try {
            userService.create(userCreateForm.getUsername(),
                    "volunteer", userCreateForm.getPassword1(), userCreateForm.getNickname());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup-volunteer";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup-volunteer";
        }

        return "redirect:/signin";
    }
}