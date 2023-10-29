package com.restsimple.demo.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CreatedStudentDTO {

    private UUID id;
    private String name;
    private String token;
    private Date created;
    private Date modified;
    private Boolean isActive = true;
       
}
