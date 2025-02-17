package com.example.triton.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String nickname;

    private String password;
    private String usertype;

    private String photoUrl;

    private Integer score;

    public String getUserType() {
        return usertype;
    }
    public Long getUId() { return uid; }
}
