package com.project.mesi;

import com.project.mesi.dto.UserDto;
import com.project.mesi.entity.User;
import com.project.mesi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void shouldInsertIntoDb() {
        final Long id = 1L;
        final String username = "rubenjt";

        //GIVEN
        UserDto userDto = new UserDto();
        //userDto.setId_user(id);
        userDto.setFirst_name("ruben");
        userDto.setLast_name("jallifier-talmat");
        userDto.setEmail("ruben@mail.com");
        userDto.setPassword("pwd");
        userDto.setSubscription_date(new Date());
        userDto.setUsername(username);
        //userDto.setRole(UserDto.RoleType.ADMIN);

        //WHEN
        //repository.save(userDto);

        //THEN
        //final Optional<UserDto> userFromDb = repository.findById(id);
        //Assertions.assertEquals(userFromDb.get().getUsername(), username);
    }
}
