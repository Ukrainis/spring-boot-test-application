package com.example.demo.exceptions;

import com.example.demo.enums.Exceptions;

import lombok.Data;

@Data
public class CustomExceptionResponse {
	private Exceptions exception;

	private String message;

	public CustomExceptionResponse(Exceptions exception, String message) {
		this.exception = exception;
		this.message = message;
	}
}