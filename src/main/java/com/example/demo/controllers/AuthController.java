package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping(path="/api/apiKeyAuthenticatedResource", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> getAuthenticatedResource() {
        return ResponseEntity.status(HttpStatus.OK).body("You are authenticated via api key!");
    }

    @GetMapping(path="/api/basicAuthAuthenticatedResource", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> getAuthenticatedViaBasicAuthResource() {
        return ResponseEntity.status(HttpStatus.OK).body("You are authenticated via Basic authentication!");
    }
}