//package com.ujjwal.quiz.service;
//
//import com.ujjwal.quiz.model.AnswerSubmission;
//import com.ujjwal.quiz.model.Question;
//import com.ujjwal.quiz.model.QuizAttempt;
//import com.ujjwal.quiz.repository.AnswerSubmissionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AnswerSubmissionServiceImpl implements  AnswerSubmissionService {
//
//    @Autowired
//    private AnswerSubmissionRepository answerSubmissionRepository;
//
//
//    @Override
//    public void submitAnswer(Question question, QuizAttempt quizAttempt, String selectedAnswer) {
//        boolean isCorrect=question.getCorrectAnswer().equalsIgnoreCase(selectedAnswer);
//
//        AnswerSubmission answerSubmission=new AnswerSubmission();
//        answerSubmission.setCorrect(isCorrect);
//        answerSubmission.setQuestion(question);
////        answerSubmission.setQuizAttempt(quizAttempt);
//        answerSubmission.setSelectedAnswer(selectedAnswer);
//
//        answerSubmissionRepository.save(answerSubmission);
//
//    }
//
//    @Override
//    public int countCorrect(QuizAttempt quizAttempt) {
//        return answerSubmissionRepository.countByQuizAttemptAndIsCorrect(quizAttempt,true);
//    }
//
//    @Override
//    public int countTotal(QuizAttempt quizAttempt) {
//        return answerSubmissionRepository.countByQuizAttempt(quizAttempt);
//    }
//}
