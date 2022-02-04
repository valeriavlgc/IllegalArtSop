package com.example.demo.controller.exceptions;

public class ErrorObject {

private Integer statusCode;
private String message;
private long timeStamp;


public int getStatusCode() {
	return statusCode;
}
public String getMessage() {
	return message;
}
public long getTimeStamp() {
	return timeStamp;
}
public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
}
public void setMessage(String message) {
	this.message = message;
}
public void setTimeStamp(long timeStamp) {
	this.timeStamp = timeStamp;
}


	
	
	
}
