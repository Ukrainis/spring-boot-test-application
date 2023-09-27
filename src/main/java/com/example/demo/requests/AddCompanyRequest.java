package com.example.demo.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@XmlRootElement
@Data
@AllArgsConstructor
public class AddCompanyRequest {

    @NotEmpty(message = "The name of the company shouldn't be empty.")
    @Size(min = 3, max = 25, message = "Company name length should be between 3 and 25 characters")
    private String name;

    @NotEmpty(message = "The catchPhrase shouldn't be empty.")
    @Size(min = 3, max = 60, message = "catchPhrase length should be between 3 and 60 characters")
    private String catchPhrase;

    @NotEmpty(message = "bs shouldn't be empty.")
    @Size(min = 3, max = 60, message = "bs length should be between 3 and 60 characters")
    private String bs;
}