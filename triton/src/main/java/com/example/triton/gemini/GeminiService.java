package com.example.triton.gemini;

import com.example.triton.help.HelpEntity;
import com.example.triton.help.HelpRepository;
import com.example.triton.help.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GeminiService {
    public static final String GEMINI_PRO = "gemini-pro";
    public static final String GEMINI_ULTIMATE = "gemini-ultimate";
    public static final String GEMINI_PRO_VISION = "gemini-pro-vision";

    private final GeminiInterface geminiInterface;

    @Autowired
    public GeminiService(GeminiInterface geminiInterface) {
        this.geminiInterface = geminiInterface;
    }

    @Autowired
    private HelpService helpService;

    private GeminiResponse getCompletion(GeminiRequest request) {
        return geminiInterface.getCompletion(GEMINI_PRO, request);
    }

    public String getCompletion(String text) {
        GeminiRequest geminiRequest = new GeminiRequest(text);
        GeminiResponse response = getCompletion(geminiRequest);

        String result = response.getCandidates()
                .stream()
                .findFirst().flatMap(candidate -> candidate.getContent().getParts()
                        .stream()
                        .findFirst()
                        .map(GeminiResponse.TextPart::getText))
                .orElse(null);

        helpService.saveHelpEntity(text, result);
        return result;
    }
}