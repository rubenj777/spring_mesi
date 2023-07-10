package com.project.mesi.dto;

import com.project.mesi.entity.Role;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String first_name;

    private String last_name;

    private String username;

    private String email;

    private String password;

    private Date subscription_date;

    private List<Role> roles;

}
