package com.example.demo.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@XmlRootElement
@Data
@AllArgsConstructor
public class CreateUserRequest {

    @NotEmpty(message = "The name of the user shouldn't be empty.")
    @Size(min = 3, max = 50, message = "User name length should be between 3 and 50 characters")
    private String name;

    @NotEmpty(message = "The username of the user shouldn't be empty.")
    @Size(min = 3, max = 20, message = "User name length should be between 3 and 20 characters")
    private String userName;

    @NotEmpty(message = "The email of the user shouldn't be empty.")
    @Email(message = "The email of the user shouldn't be empty and should be correct")
    private String email;

    private String phone;

    private String website;
}