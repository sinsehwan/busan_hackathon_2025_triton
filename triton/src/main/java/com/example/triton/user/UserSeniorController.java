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
public class UserSeniorController {

    private final UserService userService;

    @GetMapping("/signup-senior")
    public String signup(UserCreateForm userCreateForm) {
        return "signup-senior";
    }

    @PostMapping("/signup-senior")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup-senior";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 생년월일이 일치하지 않습니다.");
            return "signup-senior";
        }

        try {
            userService.create(userCreateForm.getUsername(),
                    "senior", userCreateForm.getPassword1(), userCreateForm.getUsername());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup-senior";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup-senior";
        }

        return "redirect:/signin";
    }
}