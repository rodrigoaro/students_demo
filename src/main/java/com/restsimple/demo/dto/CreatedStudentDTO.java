package com.restsimple.demo.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedStudentDTO {

    private UUID id;
    private String name;
    private String token;
    private Date created;
    private Date modified;
    private Boolean isActive = true;
    private List<AddressDTO> addresses;

       
}
