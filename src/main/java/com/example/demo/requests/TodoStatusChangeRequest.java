package com.example.demo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.validation.constraints.NotEmpty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@JacksonXmlRootElement(localName = "TodoStatusChangeRequest")
@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoStatusChangeRequest {

    @JsonProperty("status")
    @NotEmpty(message = "The status of the todo shouldn't be empty.")
    private String status;
}
