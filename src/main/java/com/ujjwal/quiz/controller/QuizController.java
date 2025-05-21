package com.ujjwal.quiz.controller;

import com.ujjwal.quiz.dto.AnswerDTO;
import com.ujjwal.quiz.dto.QuestionDTO;
import com.ujjwal.quiz.model.Player;
import com.ujjwal.quiz.repository.PlayerRepository;
import com.ujjwal.quiz.response.ApiResponse;
import com.ujjwal.quiz.service.QuestionService;
import com.ujjwal.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuizController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    QuizService quizService;
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/start")
    @PreAuthorize("hasAnyAuthority('PLAYER','ADMIN')")
    public ResponseEntity<ApiResponse> startQuiz() {
        List<QuestionDTO> questionList=questionService.displayQuestion();
        ApiResponse apiResponse=ApiResponse.builder().userList(Collections.singletonList(questionList)).statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/submit")
    @PreAuthorize("hasAuthority('PLAYER')")
    public ResponseEntity<ApiResponse> submitAnswer(@RequestBody AnswerDTO answerDTO){
       boolean isCorrect= quizService.submitAnswer(answerDTO);
       int score=quizService.getScore();
        System.out.println("Score is: "+score);

        System.out.println("Question id is:"+answerDTO.getQuestionId());
        System.out.println("Answer is: "+answerDTO.getSelectedAnswer());
       if(isCorrect){
           ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Your answer is correct!").score(score).build();
           return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
       }
        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Your answer is not correct!").score(score).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/score")
    @PreAuthorize("hasAuthority('PLAYER')")
    public ResponseEntity<ApiResponse> getScore(@PathVariable int playerId){
        int score=quizService.getScore();
        ApiResponse apiResponse=ApiResponse.builder().score(score).statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }





//    @PostMapping("/submitAnswer")
//    @PreAuthorize("hasAnyAuthority('PLAYER')")
//    public ResponseEntity<String> submitAnswer(@RequestBody AnswerRequestDTO answerRequestDTO){
//        Question question= questionService.getQuestion(answerRequestDTO.getQuestionId());
//        QuizAttempt quizAttempt=quizAttemptService.getById(answerRequestDTO.getQuizAttemptId());
//
//        answerSubmissionService.submitAnswer(question,quizAttempt,answerRequestDTO.getSelectedAnswer());
//
//        return ResponseEntity.ok("Answer Submitted");
//    }
//
//    @PostMapping("/finish/{attemptId}")
//    @PreAuthorize("hasAnyAuthority('PLAYER')")
//    public ResponseEntity<String> finishQuiz(@PathVariable int attemptId){
//        QuizAttempt quizAttempt=quizAttemptService.getById(attemptId);
//        int correct=answerSubmissionService.countCorrect(quizAttempt);
//        int total=answerSubmissionService.countTotal(quizAttempt);
//        quizAttemptService.finishQuiz(quizAttempt,correct,total);
//        return ResponseEntity.ok("Quiz Finished");
//    }
}
