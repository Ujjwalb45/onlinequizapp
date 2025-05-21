package com.ujjwal.quiz.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private int id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String category;
    private String difficulty;

    public QuestionDTO(int id,String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer, String category, String difficulty) {
       this.id=id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficulty = difficulty;
    }
}
