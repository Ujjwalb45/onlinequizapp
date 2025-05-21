package com.ujjwal.quiz.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String category;
    private String difficulty;

    @ManyToOne
    @JoinColumn(name="admin_id")
    Admin admin;
}
