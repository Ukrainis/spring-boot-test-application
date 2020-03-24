package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.helpers.Utils;
import com.example.demo.requests.AddGeoRequest;

import org.springframework.stereotype.Service;

@Service
public class GeoService{

    public boolean isGeoInvalid(AddGeoRequest geo){
        return Utils.isEmpty(geo.getLat()) || Utils.isEmpty(geo.getLng());
    }

    public Geo makeGeo(AddGeoRequest request) {
        Geo geo = new Geo();
        geo.setLat(request.getLat());
        geo.setLng(request.getLng());

        return geo;
    }

    public User updateGeoForUserAddress(User user, Geo geo) {
        user.getAddress().getGeo().setLat(geo.getLat());
        user.getAddress().getGeo().setLng(geo.getLng());

        return user;
    }
}