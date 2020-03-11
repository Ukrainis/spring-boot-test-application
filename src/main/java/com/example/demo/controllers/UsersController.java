package com.example.demo.controllers;

import java.util.List;

import com.example.demo.entities.*;
import com.example.demo.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @ApiOperation(value = "Return all existing users")
    @GetMapping(path = "api/users", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<User>> returnUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.returnAllUsers());
    }

    @ApiOperation(value = "Return user by it's ID")
    @GetMapping(path = "api/users/findById/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<User> returnUser(@PathVariable Long id) {
        User user = usersService.returnUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @ApiOperation(value = "Return user by it's UserName")
    @GetMapping(path = "api/users/findByUserName/{userName}", produces = {"application/json", "application/xml"})
    public ResponseEntity<User> returnUserByUserName(@PathVariable String userName) {
        User user = usersService.returnUserByUserName(userName);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Long.class),
            @ApiResponse(code = 400, message = "If invalid User provided"),
            @ApiResponse(code = 400, message = "If user with this username already exists")})
    @ApiOperation(value = "Add a new user")
    @PostMapping("api/user")
    public ResponseEntity<Long> createNewUser(@RequestBody User newUser) {
        Long id = usersService.addNewUser(newUser);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @ApiOperation(value = "Add or update address to an existing user")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "If valid address and valid user"),
            @ApiResponse(code = 400, message = "If invalid address provided"),
            @ApiResponse(code = 404, message = "If provided user name not exists") })
    @PutMapping("api/user/{userName}/address")
    public ResponseEntity<?> addAddressToUser(@ApiParam("Valid user user name") @PathVariable String userName,
            @ApiParam("Valid address object") @RequestBody Address address) {
        usersService.addAddressToUser(userName, address);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Add or update Geo for an existing user")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "If existing user, existing address, valid user"),
            @ApiResponse(code = 400, message = "If invalid Geo coordinates are provided"),
            @ApiResponse(code = 404, message = "If user with provided username does not exists"),
            @ApiResponse(code = 400, message = "If no defined address for provided user")})
    @PutMapping("api/user/{userName}/address/geo")
    public ResponseEntity<?> setGeoCoordinatesForUser(@ApiParam("Valid user username") @PathVariable String userName,
            @ApiParam("Valid Geo object") @RequestBody Geo geo) {
        usersService.addGeoToUserAddress(userName, geo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Add or update a Company to an existing user")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "If valid company and valid user"),
            @ApiResponse(code = 400, message = "If invalid company provided"),
            @ApiResponse(code = 404, message = "If provided user name not exists") })
    @PutMapping("api/user/{userName}/company")
    public ResponseEntity<?> addCompanyToUser(@ApiParam("Valid user user name") @PathVariable String userName,
            @ApiParam("Valid company object") @RequestBody Company company) {
        usersService.addCompanyToUser(userName, company);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    

    @ApiOperation(value = "Assign todo to user")
    @PutMapping("api/user/{userName}/todo/{todoId}")
    public ResponseEntity<?> assignTodoToUser(@PathVariable String userName, @PathVariable Long todoId) {
        usersService.assignTodoToUser(userName, todoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @ApiOperation(value = "Delete existing user")
    @DeleteMapping("api/user/{userName}")
    public ResponseEntity<?> removeUser(@PathVariable String userName) {
        usersService.removeUserByUseName(userName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}