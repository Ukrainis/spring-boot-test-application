package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@XmlRootElement
@Data
@Entity
@Table(name = "addresses")
public class Address{
    @Id    
    @JsonIgnore
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private User user;

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    @OneToOne(mappedBy = "address",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Geo geo;

    public Address (String street, String suite, String city, String zipCode){
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipCode;
    }

    public Address(){}
}