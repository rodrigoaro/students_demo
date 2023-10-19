package com.restsimple.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restsimple.demo.entity.Address;
import com.restsimple.demo.repository.AddressRepository;
import com.restsimple.demo.service.AddressService;


@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }


    
}
