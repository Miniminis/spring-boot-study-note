package com.example.exception.advice;

import com.example.exception.controllers.ApiController;
import com.example.exception.dtos.ErrorInfo;
import com.example.exception.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestControllerAdvice(basePackageClasses = ApiController.class)
public class ApiControllerAdvice {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException e,
                                                     HttpServletRequest httpServletRequest) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setMessage("");
        errorResponse.setResultCode("FAIL");

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getLocalizedMessage());

        List<ErrorInfo> errors = Arrays.asList(errorInfo);
        errorResponse.setErrorList(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                HttpServletRequest httpServletRequest) {

        List<ErrorInfo> errors = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {

            FieldError fieldError = (FieldError) error;

            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            String rejectedValue = fieldError.getRejectedValue().toString();

            ErrorInfo errorDetail = new ErrorInfo();
            errorDetail.setField(field);
            errorDetail.setMessage(message);
            errorDetail.setInvalidValue(rejectedValue);

            errors.add(errorDetail);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setMessage("");
        errorResponse.setResultCode("FAIL");
        errorResponse.setErrorList(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e,
                                                             HttpServletRequest httpServletRequest) {
        List<ErrorInfo> errors = new ArrayList<>();

        e.getConstraintViolations().forEach(error -> {

            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> streamList =  stream.collect(Collectors.toList());

            String field = streamList.get(streamList.size()-1).getName();
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            ErrorInfo errorDetail = new ErrorInfo();
            errorDetail.setField(field);
            errorDetail.setMessage(message);
            errorDetail.setInvalidValue(invalidValue);

            errors.add(errorDetail);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setMessage("");
        errorResponse.setResultCode("FAIL");
        errorResponse.setErrorList(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
