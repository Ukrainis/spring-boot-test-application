package com.example.demo.requests;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@XmlRootElement
@Data
@AllArgsConstructor
public class AddGeoRequest {

    private String lat;
    
    private String lng;
}