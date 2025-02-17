package com.example.triton.help;

import com.example.triton.help.HelpEntity;
import com.example.triton.help.HelpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/help")
public class HelpController {
    private final HelpService helpService;

    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }

    @GetMapping
    public String getAllHelps(Model model) {
        List<HelpEntity> helps = helpService.getAllHelps();
        model.addAttribute("helps", helps);
        return "helpList";
    }

    // 태그 선택에 따라 검색된 결과를 반환
    @GetMapping("/search")
    public String searchByTags(
            @RequestParam(defaultValue = "false") boolean tag1,
            @RequestParam(defaultValue = "false") boolean tag2,
            @RequestParam(defaultValue = "false") boolean tag3,
            @RequestParam(defaultValue = "false") boolean tag4,
            @RequestParam(defaultValue = "false") boolean tag5,
            Model model
    ) {
        List<HelpEntity> helps = helpService.findHelpsByTags(tag1, tag2, tag3, tag4, tag5);
        model.addAttribute("helps", helps);
        return "helpList";
    }
}

