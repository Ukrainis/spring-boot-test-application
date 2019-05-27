package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User{
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "userName", nullable = false, unique = true)
    private String userName;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Address address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "website", nullable = false)
    private String website;
    
    @OneToOne(mappedBy = "user",
                fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JsonManagedReference
    private Company company;

    public User(String name, String userName, String email, 
                String phone, String website){
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.website = website;
    }

    public User(){}
}