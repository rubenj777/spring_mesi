package com.project.mesi.service.impl;

import com.project.mesi.dto.UserDto;
import com.project.mesi.entity.Role;
import com.project.mesi.entity.User;
import com.project.mesi.repository.RoleRepository;
import com.project.mesi.repository.UserRepository;
import com.project.mesi.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(UserDto userDto,
                     MultipartFile profilePicContent
                    ) throws IOException {

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setSubscriptionDate(userDto.getSubscriptionDate());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user.setProfilePicContent(profilePicContent.getBytes());

        Role role = roleRepository.findByName("user");
        if(role != null){
            user.setRoles(List.of(role));
        }

        userRepository.save(user);
    }

    @Override
    public void update(UserDto userDto,
                       MultipartFile profilePicContent
                      ) throws IOException {
        User userToUpdate = userRepository.findOneByIdUser(userDto.getIdUser());
        userToUpdate.setUsername(userDto.getUsername());
        userToUpdate.setEmail(userToUpdate.getEmail());
        userToUpdate.setProfilePicContent(profilePicContent.getBytes());
        userRepository.save(userToUpdate);
    }

    @Override
    public void delete(User user)
    {
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findOneById(Long id) { return userRepository.findOneByIdUser(id); }
}
