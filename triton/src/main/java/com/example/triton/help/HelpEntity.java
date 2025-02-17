package com.example.triton.help;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class HelpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private boolean tag1;
    private boolean tag2;
    private boolean tag3;
    private boolean tag4;
    private boolean tag5;
}
