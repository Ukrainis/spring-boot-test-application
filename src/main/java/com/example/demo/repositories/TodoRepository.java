package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Todo;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "select * from todos where todostatus = :status", nativeQuery = true)
    List<Todo> findByStatus(@Param("status") String status);    

    @Query(value = "select * from todos where title = :title", nativeQuery = true)
    Optional<Todo> findByTitle(@Param("title") String title);
} 