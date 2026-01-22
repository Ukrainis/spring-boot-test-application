package com.example.demo.responses;

import com.example.demo.entities.User;

import jakarta.xml.bind.annotation.XmlRootElement;
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