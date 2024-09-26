package com.example.socialapp.Config;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.socialapp.DTO.ErrorDTO;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorDTO handleGenericException(HttpServletRequest request, Exception ex) {
		
		ErrorDTO error = new ErrorDTO();
		
		error.setTimestamp(new Date());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		error.setPath(request.getServletPath());
		
		
		
		
		return error;
	}
	
	
}