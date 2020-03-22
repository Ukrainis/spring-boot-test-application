package com.example.demo.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.demo.enums.Exceptions;

import lombok.Data;

@XmlRootElement
@Data
public class CustomExceptionResponse {
	private Exceptions exception;

	private String message;

	public CustomExceptionResponse(Exceptions exception, String message) {
		this.exception = exception;
		this.message = message;
	}
}