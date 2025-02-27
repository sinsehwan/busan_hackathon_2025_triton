package com.example.triton.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    SiteUser findByUsername(String username);
    Optional<SiteUser> findByusername(String username);

    Optional<SiteUser> findByUid(Long uid);

    List<SiteUser> findByUsertype(String usertype);
}
