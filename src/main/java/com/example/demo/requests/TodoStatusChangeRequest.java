package com.example.demo.requests;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@XmlRootElement
@Data
@AllArgsConstructor
public class TodoStatusChangeRequest {

    @JsonProperty("status")
    @NotEmpty(message = "The of the todo shouldn't be empty.")
    private String status;
}
