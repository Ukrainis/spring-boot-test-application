package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "geos")
public class Geo{
    @Id    
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "addressId", nullable = false)
    @JsonBackReference
    private Address address;

    private String lat;
    
    private String lng;

    public Geo(String lat, String lng){
        this.lat = lat;
        this.lng = lng;
    }

    public Geo(){}
}