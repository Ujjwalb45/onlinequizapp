//package com.ujjwal.quiz.controller;
//
//import com.ujjwal.quiz.model.Question;
//import com.ujjwal.quiz.response.ApiResponse;
//import com.ujjwal.quiz.service.QuestionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/question")
//public class QuestionController {
//
//    @Autowired
//    QuestionService questionService;
//
//    @PostMapping("/checkAnswer/{id}")
//    @PreAuthorize("hasAnyAuthority('PLAYER')")
//    public ResponseEntity<ApiResponse> checkAnswer(@PathVariable int id, @RequestBody Map<String,String>payload){
//        Question question=questionService.getQuestion(id);
//        String selectedAnswer=payload.get("answer");
//        int score =0;
//        score=score+10;
//        if(selectedAnswer.equalsIgnoreCase(question.getCorrectAnswer())){
//            ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Answer is correct.").score(score).build();
//            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
//        }
//        else{
//
//            ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.NOT_ACCEPTABLE.value()).message("Answer is not correct.").score(score).build();
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
//        }
//    }
//
// }
