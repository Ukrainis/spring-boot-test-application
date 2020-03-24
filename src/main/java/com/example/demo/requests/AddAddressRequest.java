package com.example.demo.requests;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@XmlRootElement
@Data
@AllArgsConstructor
public class AddAddressRequest {

    private String street;

    private String suite;

    private String city;

    private String zipcode;
}