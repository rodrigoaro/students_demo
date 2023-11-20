package com.restsimple.demo.service;

import com.restsimple.demo.dto.AddressDTO;
import com.restsimple.demo.entity.Address;

public interface AddressService {

    AddressDTO createAddress(Address address);   
}
