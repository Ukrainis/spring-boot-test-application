package com.example.demo.responses;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.demo.entities.User;

import lombok.Data;

@XmlRootElement
@Data
public class CreateUserResponse {
    private long Id;

    private String userName;

    public CreateUserResponse(User user) {
        Id = user.getId();
        userName = user.getUserName();
    }
}