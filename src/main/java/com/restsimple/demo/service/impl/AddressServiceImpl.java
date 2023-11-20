package com.restsimple.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restsimple.demo.dto.AddressDTO;
import com.restsimple.demo.dto.CreatedStudentDTO;
import com.restsimple.demo.entity.Address;
import com.restsimple.demo.repository.AddressRepository;
import com.restsimple.demo.service.AddressService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    private ModelMapper modelMapper;

    public AddressDTO createAddress(Address address) {
        Address addressCreated = addressRepository.save(address);
        return modelMapper.map(addressCreated, AddressDTO.class);
    }
    
}
