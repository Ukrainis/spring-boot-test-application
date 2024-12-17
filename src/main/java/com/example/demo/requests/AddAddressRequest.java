package com.example.demo.requests;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@XmlRootElement
@Data
@AllArgsConstructor
public class AddAddressRequest {

    @NotEmpty(message = "The streed shouldn't be empty.")
    @Size(min = 10, max = 40, message = "Street length should be between 10 and 40 characters")
    private String street;

    @NotEmpty(message = "The suite shouldn't be empty.")
    @Size(min = 10, max = 30, message = "Suite length should be between 10 and 30 characters")
    private String suite;

    @NotEmpty(message = "The city shouldn't be empty.")
    @Size(min = 4, max = 20, message = "City length should be between 3 and 20 characters")
    private String city;

    @NotEmpty(message = "The zipcode shouldn't be empty.")
    @Pattern.List({
            @Pattern(regexp = "^(LV-)[0-9]{4}$", message = "ZipCode should be in correct Latvian/Lithuanian format"),
            @Pattern(regexp = "^(LT-)[0-9]{5}$", message = "ZipCode should be in correct Latvian/Lithuanian format")
    })
    private String zipcode;
}
