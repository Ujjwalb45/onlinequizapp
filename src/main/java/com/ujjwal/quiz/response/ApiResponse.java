package com.ujjwal.quiz.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("statusCode")
    private int statusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("object")
    private T oneObject;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("list")
    private List<T> userList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("token")
    private String token;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("score")
    private int score;

}

