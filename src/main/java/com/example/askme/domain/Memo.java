package com.example.askme.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id")
    private Long id;
    private String question;
    private String answer;
    private LocalDateTime createdDate;
    private boolean isChecked;
}
