package com.example.demo.requests;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@JacksonXmlRootElement(localName = "AddGeoRequest")
@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddGeoRequest {

    @NotEmpty(message = "The lattitude shouldn't be empty.")
    @Pattern(regexp = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$", message = "Allowed only correct latitude value.")
    private String lat;

    @NotEmpty(message = "The longitude shouldn't be empty.")
    @Pattern(regexp = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$", message = "Allowed only correct longitude value.")
    private String lng;
}
