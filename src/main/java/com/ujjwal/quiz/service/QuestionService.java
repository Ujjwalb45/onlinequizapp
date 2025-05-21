package com.ujjwal.quiz.service;

import com.ujjwal.quiz.dto.QuestionDTO;
import com.ujjwal.quiz.model.Question;

import java.util.List;

public interface QuestionService {
    public void saveQuestion(List<Question> questionList);
    public void deleteQuestion(int id);
    public void editQuestion(int id, Question question);
    public List<QuestionDTO> displayQuestion();
    public Question getQuestion(int id);
}
