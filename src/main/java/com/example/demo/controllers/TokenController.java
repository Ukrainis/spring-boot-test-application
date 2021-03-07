package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.JWTTokenService;
import com.example.demo.services.UsersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TokenController {

    @Autowired
    JWTTokenService jwtTokenService;

    @Autowired
    UsersService usersService;

    @ApiOperation("Get jwt token for user")
    @GetMapping(path = "/api/token/{userName}/generateToken", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> generateJwtTokenForUser(@ApiParam(required = true) @PathVariable String userName) {
        User foundUser = usersService.returnUserByUserName(userName);
        String jwtToken = jwtTokenService.generateToken(foundUser);
        return ResponseEntity.status(HttpStatus.OK).body(jwtToken);
    }

}
