package com.example.demo.services;

import com.example.demo.entities.Address;
import com.example.demo.helpers.Utils;

import org.springframework.stereotype.Service;

@Service
public class AddressService {

    public boolean isAddressInvalid(Address address) {
        return Utils.isEmpty(address.getCity()) || Utils.isEmpty(address.getStreet())
                || Utils.isEmpty(address.getSuite()) || Utils.isEmpty(address.getZipcode());
    }
}