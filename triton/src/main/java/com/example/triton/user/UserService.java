package com.example.triton.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String usertype, String password, String nickname) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsertype(usertype);
        user.setPhotoUrl("");
        this.userRepository.save(user);
        return user;
    }

    public String getUserTypeByUsername(String username) {
        SiteUser user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getUserType();
        }
        return null; // 또는 예외를 던질 수도 있음
    }
}