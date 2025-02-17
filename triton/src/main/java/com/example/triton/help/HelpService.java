package com.example.triton.help;

import com.example.triton.help.HelpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpService {

    @Autowired
    private HelpRepository helpRepository;

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

    public HelpEntity saveHelpEntity(String query, String text) {
        // 사용자가 입력한 텍스트에서 키워드를 추출
        String[] extractedKeywords = extractKeywords(text);

        // 새로운 HelpEntity 객체 생성
        HelpEntity helpEntity = new HelpEntity();
        String letter = "5개의 키워드[건강, 상담, 생활, 교육, 이동]가 주어집니다. 다음으로 입력되는 상황이나 말을 분석하고, 알맞은 키워드를 최대 3개 까지 정해서 공백을 두고 키워드만 출력하세요. 억지로 키워드를 끼워 맞춰서는 안됩니다. ";
        String help = query.replace(letter, "");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = ((UserDetails) principal).getUsername();

        helpEntity.setUsername(username);
        helpEntity.setDescription(help);  // description에 텍스트 저장

        // 태그 설정
        if (containsKeyword(extractedKeywords, "건강")) {
            helpEntity.setTag1(true);
        }
        if (containsKeyword(extractedKeywords, "상담")) {
            helpEntity.setTag2(true);
        }
        if (containsKeyword(extractedKeywords, "생활")) {
            helpEntity.setTag3(true);
        }
        if (containsKeyword(extractedKeywords, "교육")) {
            helpEntity.setTag4(true);
        }
        if (containsKeyword(extractedKeywords, "이동")) {
            helpEntity.setTag5(true);
        }

        // 데이터베이스에 저장
        return helpRepository.save(helpEntity);
    }

    // 키워드 추출 메소드
    private String[] extractKeywords(String text) {
        // 예시로 간단한 키워드 추출 로직을 작성
        // 실제로는 더 복잡한 텍스트 분석 로직이 필요할 수 있음
        return text.split(" ");  // 공백 기준으로 단어를 나누는 방식
    }

    // 특정 키워드가 추출된 키워드 목록에 포함되어 있는지 확인하는 메소드
    private boolean containsKeyword(String[] keywords, String keyword) {
        for (String k : keywords) {
            if (k.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}

