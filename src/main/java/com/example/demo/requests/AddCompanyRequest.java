package com.example.demo.requests;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@XmlRootElement
@Data
@AllArgsConstructor
public class AddCompanyRequest {
        
    private String name;
    
    private String catchPhrase;
    
    private String bs;
}