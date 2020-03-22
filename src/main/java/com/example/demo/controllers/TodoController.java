package com.example.demo.controllers;

import java.util.List;

import com.example.demo.entities.Todo;
import com.example.demo.requests.TodoStatusChangeRequest;
import com.example.demo.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(path = "api/todos", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.returnAllTodos());
    }

    @ApiOperation(value = "Create new todo")
    @PostMapping(path = "api/todo", consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> addNewTodo(@RequestBody Todo todo) {
        todoService.addNewTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Change TODO status")
    @PutMapping(path = "/api/todo/{todoId}", consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> changeTodoStatus(@ApiParam @PathVariable Long todoId, @ApiParam @RequestBody TodoStatusChangeRequest newStatus) {
        todoService.changeTodoStatus(todoId, newStatus);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}