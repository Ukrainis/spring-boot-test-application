package com.example.demo.controllers;

import java.util.List;

import com.example.demo.entities.*;
import com.example.demo.requests.*;
import com.example.demo.responses.*;
import com.example.demo.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

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

    @ApiResponses(value = { @ApiResponse(code = 201, message = "User is created", response = CreateUserResponse.class),
            @ApiResponse(code = 400, message = "If invalid User provided"),
            @ApiResponse(code = 400, message = "If user with this username already exists")})
    @ApiOperation(value = "Add a new user")
    @PostMapping(path = "api/user", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<CreateUserResponse> createNewUser(@ApiParam("Valid user data") @RequestBody CreateUserRequest newUser) {
        CreateUserResponse response = usersService.addNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Add or update address to an existing user")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "If valid address and valid user"),
            @ApiResponse(code = 400, message = "If invalid address provided"),
            @ApiResponse(code = 404, message = "If provided user name not exists") })
    @PutMapping("api/user/{userName}/address")
    public ResponseEntity<?> addAddressToUser(@ApiParam("Valid user user name") @PathVariable String userName,
            @ApiParam("Valid address object") @RequestBody AddAddressRequest address) {
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
            @ApiParam("Valid Geo object") @RequestBody AddGeoRequest geo) {
        usersService.addGeoToUserAddress(userName, geo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Add or update a Company to an existing user")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "If valid company and valid user"),
            @ApiResponse(code = 400, message = "If invalid company provided"),
            @ApiResponse(code = 404, message = "If provided user name not exists") })
    @PutMapping("api/user/{userName}/company")
    public ResponseEntity<?> addCompanyToUser(@ApiParam("Valid user user name") @PathVariable String userName,
            @ApiParam("Valid company object") @RequestBody AddCompanyRequest company) {
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