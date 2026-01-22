package com.example.demo.entities;

import com.example.demo.enums.TodoStatus;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {
    
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @Column(name = "todostatus")
    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    public Todo(String title){
        this.title=title;
    }
}