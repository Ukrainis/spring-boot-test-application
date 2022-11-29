package com.example.demo.exceptions;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.demo.enums.Exceptions;

import lombok.Data;

@XmlRootElement
@Data
public class CustomExceptionResponse {
	private Exceptions exception;

	private String message;

	private Map<String, String> messages;

	public CustomExceptionResponse(Exceptions exception, String message) {
		this.exception = exception;
		this.message = message;
	}

	public CustomExceptionResponse(Exceptions exception, Map<String, String> message) {
		this.exception = exception;
		this.messages = message;
	}
}