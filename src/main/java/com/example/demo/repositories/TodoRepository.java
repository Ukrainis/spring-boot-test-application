package com.example.demo.repositories;

import com.example.demo.entities.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    
} 