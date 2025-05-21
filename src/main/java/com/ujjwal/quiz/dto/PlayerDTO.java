package com.ujjwal.quiz.dto;

import lombok.Data;

@Data
public class PlayerDTO {
    private  String username;
    private String password;
    private final String role="PLAYER";
    private String status="ONLINE";
}
