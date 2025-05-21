package com.ujjwal.quiz.controller;

import com.ujjwal.quiz.dto.QuestionDTO;
import com.ujjwal.quiz.model.Question;
import com.ujjwal.quiz.response.ApiResponse;
import com.ujjwal.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/question")
public class AdminController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/addQuestion")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> addQuestion(@RequestBody List<Question> questionList){

        questionService.saveQuestion(questionList);
        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Questions Added Successfully").build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/displayQuestion")
    @PreAuthorize("hasAnyAuthority('ADMIN','PLAYER')")
    public ResponseEntity<ApiResponse> displayQuestion(){
        List<QuestionDTO> questionList=questionService.displayQuestion();
        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).userList(Collections.singletonList(questionList)).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
