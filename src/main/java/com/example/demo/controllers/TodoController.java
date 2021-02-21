package com.example.demo.controllers;

import java.util.List;

import com.example.demo.entities.Todo;
import com.example.demo.requests.TodoStatusChangeRequest;
import com.example.demo.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController (TodoService todoService) {
        this.todoService = todoService;
    }

    @ApiOperation(value = "Return all todo's")
    @GetMapping(path = "api/todos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Todo>> getAllTodos(@ApiParam(required = false) @RequestParam(value = "status", required = false) String status) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.returnAllTodos(status));
    }

    @ApiOperation(value = "Create new todo")
    @PostMapping(path = "api/todo", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addNewTodo(@RequestBody Todo todo) {
        todoService.addNewTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Change TODO status")
    @PutMapping(path = "/api/todo/{todoId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> changeTodoStatus(@ApiParam @PathVariable Long todoId, @ApiParam @RequestBody TodoStatusChangeRequest newStatus) {
        todoService.changeTodoStatus(todoId, newStatus);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}