package com.example.triton.tag.repository;

import com.example.triton.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findTagByTagName(String tagName);
    Optional<Tag> findTagByTid(Long tid);
}
