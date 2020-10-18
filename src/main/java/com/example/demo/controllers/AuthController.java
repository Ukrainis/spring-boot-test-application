package com.example.demo.controllers;

import io.swagger.annotations.BasicAuthDefinition;
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
    @GetMapping(path="/api/authenticatedResource", produces = {"application/json", "application/xml"})
    public ResponseEntity<String> getAuthenticatedResource() {
        return ResponseEntity.status(HttpStatus.OK).body("You are authenticated!");
    }

    @ApiOperation(value = "Authenticated with Basic authorization GET operation")
    @BasicAuthDefinition(key = "Key")
    @GetMapping(path="/api/basicAuthAuthenticatedResource", produces = {"application/json", "application/xml"})
    public ResponseEntity<String> getAuthenticatedViaBasicAuthResource() {
        return ResponseEntity.status(HttpStatus.OK).body("You are authenticated via Basic authentication!");
    }
}