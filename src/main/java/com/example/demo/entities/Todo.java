package com.example.demo.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.demo.enums.TodoStatus;

import lombok.Data;

@XmlRootElement
@Data
@Entity
@Table(name = "todos")
public class Todo {
    
    @Id    
    @GeneratedValue
    private long id;

    @Column(name = "userId", nullable = true)
    private Long userId;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "todostatus")
    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    public Todo(String title){
        this.title=title;
    }

    public Todo(){}

}