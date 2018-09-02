package com.voldemort.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.voldemort.common.Result;
import com.voldemort.common.ResultCode;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {
		 
	@ExceptionHandler
	public ResponseEntity<Result<Object>> handleExceptions(Exception e) {
		 return ResponseEntity.ok().header("error", "Global unhandle exceptions")
		            .body(new Result<>().code(ResultCode.FAILED).message(e.getStackTrace()[0].toString()));
    }   
	
}

