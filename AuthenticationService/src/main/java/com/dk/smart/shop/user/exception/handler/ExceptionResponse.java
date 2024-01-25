package com.dk.smart.shop.user.exception.handler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder
(
	{  
		"status_code", 
		"status_message",
		"error_message", 
		"time_stamp",
		"path" 
	}
)
public class ExceptionResponse {

	@JsonProperty("error_message")
	private String errorMsg;
	
	@JsonProperty("status_message")
	private String statusMessage;

	@JsonProperty("status_code")
	private int statusCode;

	private String path;

	@JsonProperty("time_stamp")
	private LocalDateTime timeStamp;


	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int code) {
		this.statusCode = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}
