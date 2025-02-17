package com.example.triton.tag.repository;

import com.example.triton.tag.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {
    List<Long> findTagIdByUid(Long uid);
}
