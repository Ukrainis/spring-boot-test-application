package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Entity
@Table(name = "geos")
public class Geo {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "addressId", nullable = false)
    @JsonBackReference
    private Address address;

    @Column(length = 10)
    private String lat;

    @Column(length = 10)
    private String lng;

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }
}