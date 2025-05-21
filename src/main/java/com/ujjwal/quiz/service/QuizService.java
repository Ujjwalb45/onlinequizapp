package com.ujjwal.quiz.service;

import com.ujjwal.quiz.dto.AnswerDTO;

public interface QuizService {
    public boolean submitAnswer(AnswerDTO answerRequestDTO);
    int getScore();
}
