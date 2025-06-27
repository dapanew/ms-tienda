package com.famisanar.tienda.ms_tienda.infraestructure.entity.dto;



import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private String message;
    private T data;
    private boolean success;

    public ApiResponse(T data, String message, boolean success) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
        this.success = success;
    }
}