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

    private Long idUser;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private Date subscriptionDate;

    private byte[] profilePicContent;

    private List<Role> roles;

}
