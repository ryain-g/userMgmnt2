package com.hnbafrica.userMgmt.dto;

import com.hnbafrica.userMgmt.entity.Role;
import lombok.Data;

import javax.persistence.Column;
import java.util.Set;

@Data
public class UserRegistrationDto {

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private int phone;

    private Set<Role> roles
}
