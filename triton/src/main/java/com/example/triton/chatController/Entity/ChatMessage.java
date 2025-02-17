package com.example.triton.chatController.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter @Setter
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mId;
    private String message;
    private Integer cId;
    private Date mTime;

    public ChatMessage(String message, int cId, Date mTime){
        this.message = message;
        this.cId = cId;
        this.mTime = mTime;
    }

    public ChatMessage(){
    }
}
