package com.example.demo.repositories;

import com.example.demo.entities.Geo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoRepository extends JpaRepository<Geo, Long>{
    
}