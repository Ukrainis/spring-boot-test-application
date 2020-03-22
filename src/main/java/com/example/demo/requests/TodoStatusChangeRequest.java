package com.example.demo.requests;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@XmlRootElement
@Data
@AllArgsConstructor
public class TodoStatusChangeRequest {

    @JsonProperty("status")
    private String status;
}