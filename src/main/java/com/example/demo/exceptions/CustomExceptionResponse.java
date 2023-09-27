package com.example.demo.exceptions;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.demo.enums.Exceptions;

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