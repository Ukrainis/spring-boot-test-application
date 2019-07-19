package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.helpers.Utils;

import org.springframework.stereotype.Service;

@Service
public class GeoService{

    public boolean isGeoInvalid(Geo geo){
        return Utils.isEmpty(geo.getLat()) || Utils.isEmpty(geo.getLng());
    }

    public User updateGeoForUserAddress(User user, Geo geo) {
        user.getAddress().getGeo().setLat(geo.getLat());
        user.getAddress().getGeo().setLng(geo.getLng());

        return user;
    }
}