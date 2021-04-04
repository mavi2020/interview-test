package com.surena.interview;

import com.surena.interview.dto.UserDto;
import com.surena.interview.mapper.UserMapper;
import com.surena.interview.model.User;
import com.surena.interview.repository.UserRepository;
import com.surena.interview.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest2 {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    public void newUser() {
        UserDto userDtoNewUser = new UserDto("mavi", "321", "shm", "mousavi");
        when(userRepository.save(any(User.class))).thenReturn(new User());
        UserDto result = userService.create(userDtoNewUser);
        assertThat(result.getUsername()).isEqualTo(userDtoNewUser.getUsername());
    }

}