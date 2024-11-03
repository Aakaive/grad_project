package com.sparta.grad_project.global.exception;


import com.sparta.grad_project.global.config.ApiResponse;
import com.sparta.grad_project.global.exception.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    
    // GlobalException 처리
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleGlobalException(GlobalException e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ApiResponse.error(new ErrorResponse(e.getHttpStatus().value(), e.getMessage())));
    }

    // RuntimeException 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleRuntimeException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(500, e.getMessage());
        return ResponseEntity
                .status(500)
                .body(ApiResponse.error(errorResponse));
    }
}
