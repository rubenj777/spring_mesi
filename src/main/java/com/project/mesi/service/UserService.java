package com.project.mesi.service;

import com.project.mesi.dto.UserDto;
import com.project.mesi.entity.User;

public interface UserService
{

    void save(UserDto userDto);

    User findByUsername(String username);

    User findByEmail(String email);

}
