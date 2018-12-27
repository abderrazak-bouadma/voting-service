package com.louvrehotels.votingservice.controller;

import com.louvrehotels.votingservice.service.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ServiceException.class)
    ResponseEntity<ApiError> handleServiceException(ServiceException e) {
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(apiError);
    }
}
