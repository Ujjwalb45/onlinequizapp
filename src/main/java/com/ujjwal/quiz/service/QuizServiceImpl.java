package com.ujjwal.quiz.service;

import com.ujjwal.quiz.dto.AnswerDTO;
import com.ujjwal.quiz.model.AnswerSubmission;
import com.ujjwal.quiz.model.Player;
import com.ujjwal.quiz.model.Question;
import com.ujjwal.quiz.repository.AnswerSubmissionRepository;
import com.ujjwal.quiz.repository.PlayerRepository;
import com.ujjwal.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerSubmissionRepository answerSubmissionRepository;



    @Override
    public boolean submitAnswer(AnswerDTO answerDTO) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Player player=playerRepository.findByUsername(authentication.getName());

        Question question=questionRepository.findById(answerDTO.getQuestionId()).orElseThrow(()->new RuntimeException("Question not found!"));

        boolean isCorrect=question.getCorrectAnswer().equalsIgnoreCase(answerDTO.getSelectedAnswer());

        AnswerSubmission answerSubmission= new AnswerSubmission();
        answerSubmission.setPlayer(player);
        answerSubmission.setSelectedAnswer(answerDTO.getSelectedAnswer());
        answerSubmission.setCorrect(isCorrect);
        answerSubmission.setQuestion(question);
        answerSubmissionRepository.save(answerSubmission);

        if(isCorrect){
            player.setScore(player.getScore()+1);
            playerRepository.save(player);
        }
        return  isCorrect;

    }

    @Override
    public int getScore() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        Player player=playerRepository.findByUsername(authentication.getName());
        return  player.getScore();
    }
}
