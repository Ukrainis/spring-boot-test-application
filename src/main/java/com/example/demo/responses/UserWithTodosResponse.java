package com.example.demo.responses;

import com.example.demo.entities.Address;
import com.example.demo.entities.Company;
import com.example.demo.entities.Todo;
import com.example.demo.entities.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
public class UserWithTodosResponse {

    private long id;

    private String name;

    private String userName;

    private String email;

    private Address address;

    private String phone;

    private String website;

    private Company company;

    List<Todo> todoList;
}
