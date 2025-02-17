package com.example.triton.help;

import com.example.triton.help.HelpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpRepository extends JpaRepository<HelpEntity, Long> {

    // tag1, tag2, tag3, tag4, tag5 중 하나라도 true인 경우를 포함해서 검색
    @Query("SELECT h FROM HelpEntity h WHERE " +
            "(:tag1 = true AND h.tag1 = true) OR " +
            "(:tag2 = true AND h.tag2 = true) OR " +
            "(:tag3 = true AND h.tag3 = true) OR " +
            "(:tag4 = true AND h.tag4 = true) OR " +
            "(:tag5 = true AND h.tag5 = true)")
    List<HelpEntity> findByAnySelectedTag(boolean tag1, boolean tag2, boolean tag3, boolean tag4, boolean tag5);
}


