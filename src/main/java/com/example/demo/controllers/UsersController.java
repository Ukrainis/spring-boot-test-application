package com.example.demo.controllers;

import com.example.demo.auth.UserPrincipal;
import com.example.demo.entities.Todo;
import com.example.demo.entities.User;
import com.example.demo.exceptions.*;
import com.example.demo.requests.AddAddressRequest;
import com.example.demo.requests.AddCompanyRequest;
import com.example.demo.requests.AddGeoRequest;
import com.example.demo.requests.CreateUserRequest;
import com.example.demo.responses.CreateUserResponse;
import com.example.demo.services.JWTTokenService;
import com.example.demo.services.TodoService;
import com.example.demo.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
public class UsersController {

        @Autowired
        private UsersService usersService;

        @Autowired
        private TodoService todoService;

        @Autowired
        private JWTTokenService jwtTokenService;

        @Operation(description = "Return all existing users")
        @ApiResponse(responseCode = "200", description = "All existing users", content = {
                        @Content(schema = @Schema(implementation = List.class)) })
        @GetMapping(path = "api/users", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        public ResponseEntity<List<User>> returnUsers() {
                return ResponseEntity.status(HttpStatus.OK).body(usersService.returnAllUsers());
        }

        @Operation(description = "Return user by it's ID")
        @ApiResponse(responseCode = "404", description = "User not found", content = {
                        @Content(schema = @Schema(implementation = User.class)) })
        @GetMapping(path = "api/users/findById/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        public ResponseEntity<User> returnUser(@PathVariable Long id) {
                User user = usersService.returnUserById(id);
                return ResponseEntity.status(HttpStatus.OK).body(user);
        }

        @Operation(description = "Return user by it's UserName")
        @ApiResponse(responseCode = "404", description = "User not found", content = {
                        @Content(schema = @Schema(implementation = UserNotFoundException.class)) })
        @GetMapping(path = "api/users/findByUserName/{userName}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        public ResponseEntity<User> returnUserByUserName(@PathVariable String userName) {
                User user = usersService.returnUserByUserName(userName);
                return ResponseEntity.status(HttpStatus.OK).body(user);
        }

        @Operation(description = "Add a new user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "User is created", content = {
                                        @Content(schema = @Schema(implementation = CreateUserResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "If invalid User provided or user with this username already exists", content = {
                                        @Content(schema = @Schema(implementation = DuplicatedUserNameException.class)) })
        })
        @PostMapping(path = "api/user/createUserPost", consumes = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {
                                        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        public ResponseEntity<CreateUserResponse> createNewUser(
                        @Parameter(description = "Valid user data") @Valid @RequestBody CreateUserRequest newUser) {
                CreateUserResponse response = usersService.addNewUser(newUser);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "User is created", content = {
                                        @Content(schema = @Schema(implementation = CreateUserResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "If invalid User provided or user with this username already exists") })
        @Operation(description = "Add a new user")
        @PostMapping(path = "api/user/createUserUrl", consumes = {
                        MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        public ResponseEntity<CreateUserResponse> createNewUserUsingUrlEncoded(CreateUserRequest newUser) {
                CreateUserResponse response = usersService.addNewUser(newUser);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        @Operation(description = "Add or update address to an existing user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "If valid address and valid user"),
                        @ApiResponse(responseCode = "400", description = "If invalid address provided", content = {
                                        @Content(schema = @Schema(implementation = InvalidAddressDataException.class)) }),
                        @ApiResponse(responseCode = "404", description = "If provided user name not exists", content = {
                                        @Content(schema = @Schema(implementation = UserNotFoundException.class)) })
        })
        @PutMapping(path = "api/user/{userName}/address", consumes = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        public ResponseEntity<?> addAddressToUser(
                        @Parameter(description = "Valid user user name") @PathVariable String userName,
                        @Parameter(description = "Valid address object") @RequestBody AddAddressRequest address) {
                usersService.addAddressToUser(userName, address);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        @Operation(description = "Add or update Geo for an existing user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "If existing user, existing address, valid user"),
                        @ApiResponse(responseCode = "400", description = "If invalid Geo coordinates or no defined address for user provided"),
                        @ApiResponse(responseCode = "404", description = "If user with provided username does not exists", content = {
                                        @Content(schema = @Schema(implementation = UserNotFoundException.class)) })
        })
        @PutMapping(path = "api/user/{userName}/address/geo", consumes = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        public ResponseEntity<?> setGeoCoordinatesForUser(
                        @Parameter(description = "Valid user username") @PathVariable String userName,
                        @Parameter(description = "Valid Geo object") @RequestBody AddGeoRequest geo) {
                usersService.addGeoToUserAddress(userName, geo);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        @Operation(description = "Add or update a Company to an existing user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "If valid company and valid user"),
                        @ApiResponse(responseCode = "400", description = "If invalid company provided", content = {
                                        @Content(schema = @Schema(implementation = InvalidCompanyDataException.class)) }),
                        @ApiResponse(responseCode = "404", description = "If provided user name not exists", content = {
                                        @Content(schema = @Schema(implementation = UserNotFoundException.class)) })
        })
        @PutMapping(path = "api/user/{userName}/company", consumes = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        public ResponseEntity<?> addCompanyToUser(
                        @Parameter(description = "Valid user user name") @PathVariable String userName,
                        @Parameter(description = "Valid company object") @RequestBody AddCompanyRequest company) {
                usersService.addCompanyToUser(userName, company);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        @Operation(description = "Assign todo to user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "404", description = "User or TODO is not found"),
                        @ApiResponse(responseCode = "424", description = "User is not completed", content = {
                                        @Content(schema = @Schema(implementation = NotCompletedUserDataException.class)) })
        })
        @PutMapping("api/user/{userName}/assignTodo/{todoId}")
        public ResponseEntity<?> assignTodoToUser(
                        @Parameter(description = "Valid user user name") @PathVariable String userName,
                        @Parameter(description = "Existing TODO id") @PathVariable Long todoId) {
                usersService.assignTodoToUser(userName, todoId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        @Operation(description = "Delete existing user")
        @ApiResponse(responseCode = "404", description = "User not found", content = {
                        @Content(schema = @Schema(implementation = UserNotFoundException.class)) })
        @DeleteMapping("api/user/{userName}/delete")
        public ResponseEntity<?> removeUser(@PathVariable String userName) {
                usersService.removeUserByUseName(userName);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        @Operation(description = "Get user's todos")
        @GetMapping("api/user/{userName}/todos")
        public ResponseEntity<List<Todo>> getUserWithTodos(
                        @Parameter(required = true) @PathVariable String userName,
                        @Parameter(required = false) @RequestHeader("Authorization") String jwtToken) {
                if (jwtToken == null)
                        throw new TokenNotFoundException();

                UserPrincipal userPrincipal = jwtTokenService.parseToken(jwtToken);
                if (!userPrincipal.getUserName().equalsIgnoreCase(userName))
                        throw new InvalidUserTokenException();

                User user = usersService.returnUserByUserName(userName);
                List<Todo> list = todoService.returnAllTodos()
                                .stream().filter(todo -> (todo.getUserId() != null && todo.getUserId() == user.getId()))
                                .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK).body(list);
        }
}
