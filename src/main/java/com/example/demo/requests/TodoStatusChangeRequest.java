package com.example.demo.requests;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@XmlRootElement
@Data
@AllArgsConstructor
public class TodoStatusChangeRequest {

    @JsonProperty("status")
    @NotEmpty(message = "The of the todo shouldn't be empty.")
    private String status;
}
