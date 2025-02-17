package com.example.triton.chatController.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Getter @Setter
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cId;
    private String chatName;
    private Integer vId;
    private Integer agedId;

    public Chatting(String chatName, Integer vId, Integer agedId){
        this.vId = vId;
        this.agedId = agedId;
    }

    public Chatting() {

    }

}
