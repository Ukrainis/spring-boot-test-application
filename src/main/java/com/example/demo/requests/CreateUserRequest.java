package com.example.demo.requests;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@XmlRootElement
@Data
@AllArgsConstructor
public class CreateUserRequest {
    private String name;

    private String userName;

    private String email;

    private String phone;

    private String website;
}