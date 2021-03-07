package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.enums.TodoStatus;
import com.example.demo.exceptions.*;
import com.example.demo.helpers.Utils;
import com.example.demo.repositories.TodoRepository;
import com.example.demo.requests.TodoStatusChangeRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    
    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository=todoRepository;
    }

    public List<Todo> returnAllTodos() {
        return todoRepository.findAll();
    }

    public void addNewTodo(Todo todo) {
        if (isTodoInvalid(todo)) {
            throw new InvalidTodoException();
        }

        Optional<Todo> todoActual = todoRepository.findByTitle(todo.getTitle());

        if (todoActual.isPresent()) {
            throw new DublicatedTodoException(todo.getTitle());
        }
        todo.setStatus(TodoStatus.TODO);

        todoRepository.save(todo);
    }

    public void changeTodoStatus(Long todoId, TodoStatusChangeRequest newStatus) {
        String status = newStatus.getStatus();
        if (Utils.isEmpty(status) || isTodoStatusValid(status)) {
            throw new InvalidTodoStatusException(status);
        }

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));

        if (todo.getUserId() == null) {
            throw new TodoIsNotAssignedException();
        }

        if ((todo.getStatus().status.equals(TodoStatus.TODO.status)) && status.equals(TodoStatus.DONE.status)) {
            throw new WrongTodoStatusException("TODO in Todo status can't be changed to Done status without moving to In progress status");
        }

        if ((todo.getStatus().status.equals(TodoStatus.DONE.status)) && 
            (status.equals(TodoStatus.TODO.status) || status.equals(TodoStatus.IN_PROGRESS.status))) {
            throw new WrongTodoStatusException("TODO in Done status can't be changed to Todo status or back in to In progress status");
        }

        if ((todo.getStatus().status.equals(TodoStatus.TODO.status)) && status.equals(TodoStatus.IN_PROGRESS.status)) {
            todo.setStatus(TodoStatus.IN_PROGRESS);
            todoRepository.save(todo);
        }

        if ((todo.getStatus().status.equals(TodoStatus.IN_PROGRESS.status)) && status.equals(TodoStatus.DONE.status)) {
            todo.setStatus(TodoStatus.DONE);
            todoRepository.save(todo);
        }
    }

    private Boolean isTodoInvalid(Todo todo) {
        return Utils.isEmpty(todo.getTitle());
    }

    private Boolean isTodoStatusValid(String status) {
        return (!status.equals(TodoStatus.IN_PROGRESS.status)) && (!status.equals(TodoStatus.DONE.status)) && (!status.equals(TodoStatus.TODO.status));
    }
}