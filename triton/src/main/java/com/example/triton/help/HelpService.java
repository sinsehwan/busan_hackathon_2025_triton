package com.example.triton.help;

import com.example.triton.help.HelpEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpService {
    private final HelpRepository helpRepository;

    public HelpService(HelpRepository helpRepository) {
        this.helpRepository = helpRepository;
    }

    public List<HelpEntity> getAllHelps() {
        return helpRepository.findAll();
    }

    public HelpEntity saveHelp(HelpEntity help) {
        return helpRepository.save(help);
    }

    public void deleteHelp(Long id) {
        helpRepository.deleteById(id);
    }

    // 선택된 태그에 따라 검색
    public List<HelpEntity> findHelpsByTags(boolean tag1, boolean tag2, boolean tag3, boolean tag4, boolean tag5) {
        return helpRepository.findByAnySelectedTag(tag1, tag2, tag3, tag4, tag5);
    }
}

