package com.example.demo.controllers;

import io.swagger.annotations.BasicAuthDefinition;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;

@RestController
public class AuthController {
    
    @ApiOperation(value = "Authenticated get operation")
    @ApiKeyAuthDefinition(in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER, name = "Auth-Key", key = "Key")
    @GetMapping(path="/api/apiKeyAuthenticatedResource", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> getAuthenticatedResource() {
        return ResponseEntity.status(HttpStatus.OK).body("You are authenticated via api key!");
    }

    @ApiOperation(value = "Authenticated with Basic authorization GET operation")
    @BasicAuthDefinition(key = "Key")
    @GetMapping(path="/api/basicAuthAuthenticatedResource", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> getAuthenticatedViaBasicAuthResource() {
        return ResponseEntity.status(HttpStatus.OK).body("You are authenticated via Basic authentication!");
    }
}