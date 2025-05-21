package com.ujjwal.quiz.service;

import com.ujjwal.quiz.dto.QuestionDTO;
import com.ujjwal.quiz.model.Admin;
import com.ujjwal.quiz.model.Question;
import com.ujjwal.quiz.repository.AdminRepository;
import com.ujjwal.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public void saveQuestion(List<Question> questionList) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Admin admin=adminRepository.findByUsername(authentication.getName());
        for(Question questions:questionList){
            questions.setAdmin(admin);
        }
        questionRepository.saveAll(questionList);


    }

    @Override
    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }

    @Override
    public void editQuestion(int id, Question question) {

    }

    @Override
    public List<QuestionDTO> displayQuestion() {
        List<QuestionDTO> questionList=new ArrayList<>();
        questionList= questionRepository.findAll().stream()
                .map(q -> new QuestionDTO(q.getId(),q.getQuestion(), q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD(), q.getCorrectAnswer(), q.getCategory(), q.getDifficulty()))
                .collect(Collectors.toList());
        return questionList;
    }

    @Override
    public Question getQuestion(int id) {
        Question question=questionRepository.findById(id).orElse(null);
        return question;
    }
}
