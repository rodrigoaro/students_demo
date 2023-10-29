package com.restsimple.demo.entity;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.beans.factory.annotation.Value;

import com.restsimple.demo.validation.EmailConstraint;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "student_id")
    private UUID id;

    @NotBlank(message = "Name field is mandatory")
    private String name;
    
    @EmailConstraint
    @NotBlank(message = "Email field is mandatory")
    private String email;
    
    @NotBlank(message = "Password field is mandatory")
    private String password;
    
    private String token;
    
    @JoinColumn(name = "student_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true)
    private Set<Address> addresses;
    
    private Date created;
    
    private Date modified;

}
