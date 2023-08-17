package com.project.mesi.service;

import com.project.mesi.dto.UserDto;
import com.project.mesi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService
{

    void save(UserDto userDto, MultipartFile profilePicContent) throws IOException;

    void update(UserDto userDto, MultipartFile profilePicContent) throws IOException;

    void delete(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    User findOneById(Long id);

}
