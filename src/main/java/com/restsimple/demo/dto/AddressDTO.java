package com.restsimple.demo.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String number;

    @NotBlank
    @JsonProperty(value = "citycode")
    private String cityCode;

    @NotBlank
    private UUID studentId;
    
}
