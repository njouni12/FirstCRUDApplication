package com.example.CRUDSecuredApplication.Advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String,String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage()); //field of the request,msg that we wrote
		});
		return errorMap;
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public Map<String,String> handleEmployeeNotFound(EmployeeNotFoundException ex){
		Map<String,String> errorMap = new HashMap<>();
		errorMap.put("Error message", ex.getMessage());
		return errorMap;
	}
	
}
