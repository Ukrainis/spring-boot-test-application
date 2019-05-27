package com.example.demo.exceptions;

import lombok.Data;

@Data
public class CustomExceptionResponse {
	private String error;
	
	public CustomExceptionResponse(String error) {
		this.error = error;
	}
}
//TODO Add one more field and parameter into constructor - Exception type