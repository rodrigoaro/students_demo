package com.restsimple.demo.dto;

import java.util.Date;
import java.util.List;

import com.restsimple.demo.entity.Address;
import com.restsimple.demo.validation.EmailConstraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @NotBlank(message = "Name field is mandatory")
    private String name;

    @EmailConstraint
    @NotBlank(message = "Email field is mandatory")
    private String email;

    @NotBlank(message = "Password field is mandatory")
    private String password;

    private String token;

    @NotNull
    private List<Address> addresses;
    
    private Date created;
    private Date modified;
    
}
