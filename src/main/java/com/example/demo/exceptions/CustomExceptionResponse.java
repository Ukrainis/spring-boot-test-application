package com.example.demo.exceptions;

import java.util.Map;

import com.example.demo.enums.Exceptions;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@XmlRootElement
@Data
@AllArgsConstructor
public class CustomExceptionResponse {
	private Exceptions exception;

	private String message;

	private Map<String, String> errors;
}