package com.hnbafrica.userMgmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;

@Data
@JsonIgnoreProperties(value={ "password" },allowSetters= true)
public class User {
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "phone")
    private String phone;

}
