package com.example.triton.assist;

import com.example.triton.help.HelpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistService {

    @Autowired
    private AssistRepository assistRepository;

    public List<AssistEntity> getAllAssists() {
        return assistRepository.findAll();
    }

    public AssistEntity saveAssistEntity(String query, String text) {

        AssistEntity assistEntity = new AssistEntity();
        String letter = "노인들의 사회진출 목적으로 재능 기부가 가능한 요건들이 주어집니다. 다음으로 주어지는 요건을 분석하여 해당 항목을 수행하기 추천하는 노인을 관련된 내용을 아주 짧게 감탄문으로 제공하세요. 기분이 나쁘지 않을 수 있도록 '노인'이라는 단어는 뺴도록 하세요. ";
        String assist = query.replace(letter, "");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = ((UserDetails) principal).getUsername();

        assistEntity.setUsername(username);
        assistEntity.setDescription(assist);
        assistEntity.setSummary(text);

        return assistRepository.save(assistEntity);
    }
}
