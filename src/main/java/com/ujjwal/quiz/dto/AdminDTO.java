package com.ujjwal.quiz.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private String username,password;
    private final String role="ADMIN";
}
