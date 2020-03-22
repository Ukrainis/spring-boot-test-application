package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.enums.TodoStatus;
import com.example.demo.exceptions.*;
import com.example.demo.helpers.Utils;
import com.example.demo.repositories.TodoRepository;
import com.example.demo.requests.TodoStatusChangeRequest;

import java.util.List;
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
        todo.setStatus(TodoStatus.TODO);

        todoRepository.save(todo);
    }

    public void changeTodoStatus(Long todoId, TodoStatusChangeRequest newStatus) {
        String status = newStatus.getStatus();
        if (!status.equals(TodoStatus.IN_PROGRESS.status) || !status.equals(TodoStatus.DONE.status)) {
            throw new InvalidTodoStatusException(status);
        }

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));

        if (todo.getUserId() == null) {
            throw new TodoIsNotAssignedException();
        }

        if ((todo.getStatus().status == TodoStatus.TODO.status) && status == TodoStatus.DONE.status) {

        }
    }

    private Boolean isTodoInvalid(Todo todo) {
        return Utils.isEmpty(todo.getTitle());
    }
}