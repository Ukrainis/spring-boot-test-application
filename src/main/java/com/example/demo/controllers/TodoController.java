package com.example.demo.controllers;

import java.util.List;

import com.example.demo.entities.Todo;
import com.example.demo.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

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
    @PostMapping("api/todo")
    public ResponseEntity<?> addNewTodo(@RequestBody Todo todo) {
        todoService.AddNewTodo(todo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}