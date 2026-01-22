package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    //@ApiModelProperty(hidden = true)
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private User user;

    private String name;
    
    private String catchPhrase;
    
    private String bs;

    public Company(String name, String catchPhrase, String bs){
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }
}