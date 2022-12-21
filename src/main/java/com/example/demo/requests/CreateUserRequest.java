package com.example.demo.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
    @Size(min = 5, max = 30, message = "User email length should be between 5 and 30")
    @Email(message = "The email of the user should be correct")
    private String email;

    @Pattern(regexp = "^(\\+|00)37(06\\d{7}|12\\d{7})$", message = "Allowed only Latvian and Lithuanian numbers.")
    private String phone;

    @Size(min = 5, max = 30, message = "Website length should be between 5 and 30")
    @Pattern(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9 @:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)", message = "Website should be in correct format")
    private String website;
}