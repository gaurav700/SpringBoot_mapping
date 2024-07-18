package com.mapping.homework.mapping.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private T data;
    private ApiError error;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ApiResponse(ApiError error) {
        this.error = error;
    }
}
