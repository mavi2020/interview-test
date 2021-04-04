package com.surena.interview;

import com.surena.interview.dto.UserDto;
import com.surena.interview.mapper.UserMapper;
import com.surena.interview.repository.UserRepository;
import com.surena.interview.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void newUser() {
        UserDto userDtoNewUser = new UserDto("mavi", "321", "shm", "mousavi");
        userDtoNewUser = userService.create(userDtoNewUser);
        UserDto userDtoFindByUsername = userService.getByUsername("mavi");
        assertThat(userDtoNewUser.getId()).isEqualTo(userDtoFindByUsername.getId());
    }

    @Test
    public void findByUserName() {
        UserDto userDtoSearchByName = userService.getByUsername("mavi");
        assertThat(userDtoSearchByName.getId()).isEqualTo(0);
    }
}