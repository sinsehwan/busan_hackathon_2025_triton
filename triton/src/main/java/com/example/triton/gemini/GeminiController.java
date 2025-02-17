package com.example.triton.gemini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    @Autowired
    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @ResponseBody
    @GetMapping("/completion")
    public String getCompletion(@RequestParam String text) {
        return geminiService.getCompletion(text);
    }
}