package com.ujjwal.quiz.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AnswerSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String selectedAnswer;
    private boolean isCorrect;

    @ManyToOne
    Question question;

    @ManyToOne
    @JoinColumn(name="player_id")
    Player player;

}
