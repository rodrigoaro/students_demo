package com.restsimple.demo.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.restsimple.demo.entity.Address;

@Component
public interface AddressRepository extends JpaRepository<Address, UUID>{
    
}
