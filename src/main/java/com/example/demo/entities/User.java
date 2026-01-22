package com.example.demo.entities;

import com.example.demo.requests.CreateUserRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false, length = 51)
    private String name;

    @Column(name = "userName", nullable = false, unique = true, length = 21)
    private String userName;

    @Column(name = "email", nullable = false, length = 31)
    private String email;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Address address;

    @Column(name = "phone", nullable = false, length = 16)
    private String phone;

    @Column(name = "website", nullable = false, length = 31)
    private String website;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Company company;

    public User(CreateUserRequest request) {
        this.name = request.getName();
        this.userName = request.getUserName();
        this.email = request.getEmail();
        this.phone = request.getPhone();
        this.website = request.getWebsite();
    }
}