package com.ujjwal.quiz.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@Entity
//@Data
//public class QuizAttempt {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//    private int totalQuestions;
//    private int correctAnswers;
//    private int score;
//
//    @ManyToOne
//    @JoinColumn(name="player_id")
//    Player player;
//
//    @OneToMany(mappedBy = "quizAttempt",cascade = CascadeType.ALL)
//    private List<AnswerSubmission> submissions;
//}
