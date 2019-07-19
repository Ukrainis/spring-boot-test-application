package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.enums.TodoStatus;
import com.example.demo.exceptions.InvalidTodoException;
import com.example.demo.helpers.Utils;
import com.example.demo.repositories.TodoRepository;
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

    public void AddNewTodo(Todo todo) {
        if (isTodoInvalid(todo)) {
            throw new InvalidTodoException();
        }
        todo.setStatus(TodoStatus.TODO);

        todoRepository.save(todo);
    }

    private Boolean isTodoInvalid(Todo todo) {
        return Utils.isEmpty(todo.getTitle());
    }
}