package com.dk.smart.shop.user.exception.handler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ExceptionResponse> handleSQLException(
			SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException, final HttpServletRequest request) {

		ExceptionResponse errorResponse = new ExceptionResponse();

		String statusMessage = HttpStatus.NOT_ACCEPTABLE.toString();
		errorResponse.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		errorResponse.setStatusMessage(statusMessage.substring(statusMessage.indexOf(" ") + 1));
		errorResponse.setErrorMsg(sqlIntegrityConstraintViolationException.getMessage());
		errorResponse.setPath(request.getRequestURI());
		errorResponse.setTimeStamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse);
	}
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ExceptionResponse> handleAuthException(
			AuthorizationException authorizationException, final HttpServletRequest request) {
		
		ExceptionResponse errorResponse = new ExceptionResponse();
		
		String statusMessage = HttpStatus.UNAUTHORIZED.toString();
		errorResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		errorResponse.setStatusMessage(statusMessage.substring(statusMessage.indexOf(" ") + 1));
		errorResponse.setErrorMsg(authorizationException.getMessage());
		errorResponse.setPath(request.getRequestURI());
		errorResponse.setTimeStamp(LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	}
}
