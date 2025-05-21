//package com.ujjwal.quiz.service;
//
//import com.ujjwal.quiz.model.Player;
//import com.ujjwal.quiz.model.QuizAttempt;
//import com.ujjwal.quiz.repository.QuestionRepository;
//import com.ujjwal.quiz.repository.QuizAttemptRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class QuizAttemptServiceImpl implements QuizAttemptService {
//
//    @Autowired
//    QuizAttemptRepository quizAttemptRepository;
//
//    @Autowired
//    QuestionRepository questionRepository;
//
//    @Override
//    public QuizAttempt startQuiz(Player player) {
//        QuizAttempt quizAttempt= new QuizAttempt();
//
//        int totalQuestion= (int) questionRepository.count();
//
//        quizAttempt.setPlayer(player);
//        quizAttempt.setStartTime(LocalDateTime.now());
//        quizAttempt.setTotalQuestions(totalQuestion);
//        quizAttempt.setScore(0);
//        quizAttempt.setCorrectAnswers(0);
//        return  quizAttemptRepository.save(quizAttempt);
//    }
//
//    @Override
//    public QuizAttempt getById(int id) {
//        return quizAttemptRepository.findById(id).orElseThrow(()-> new RuntimeException("Quiz Attempt not found"));
//    }
//
//    @Override
//    public void finishQuiz(QuizAttempt quizAttempt, int correct, int total) {
//        quizAttempt.setCorrectAnswers(correct);
//        quizAttempt.setTotalQuestions(total);
//        quizAttempt.setScore((correct*10)/total);
//        quizAttempt.setEndTime(LocalDateTime.now());
//        quizAttemptRepository.save(quizAttempt);
//
//    }
//}
