package com.surena.interview.service;

import com.surena.interview.dto.ChangePasswordDto;
import com.surena.interview.dto.UserDto;
import com.surena.interview.exception.BadRequestException;
import com.surena.interview.exception.NotFoundException;
import com.surena.interview.mapper.UserMapper;
import com.surena.interview.model.User;
import com.surena.interview.repository.UserRepository;
import com.surena.interview.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest2 {

    //@InjectMocks
    @Autowired
    private UserService userService;

    @Test(expected = BadRequestException.class)
    public void createTest() {
        UserDto userDto = new UserDto("mavi", "12345", "shima", "mousavi");
        userService.create(userDto);
        userService.create(userDto);
    }

    @Test(expected = NotFoundException.class)
    public void updateUsernameNotFoundTest() {
        UserDto userDto = new UserDto("user", "1", "shima", "m");
        userService.update(userDto);
    }

    @Test(expected = BadRequestException.class)
    public void updateNoChangeTest() {
        UserDto userDto = new UserDto("mavi", "12345", "shima", "mousavi");
        userService.create(userDto);
        userService.update(userDto);
    }

    @Test(expected = BadRequestException.class)
    public void changePasswordIncorrectPasswordTest(){
        UserDto userDto = new UserDto("mavi", "12345", "shima", "mousavi");
        userService.create(userDto);
        ChangePasswordDto passwordDto= new ChangePasswordDto("mavi","1234","123");
        userService.changePassword(passwordDto);
    }

    @Test(expected = NotFoundException.class)
    public void changePasswordNotFoundUser(){
        UserDto userDto = new UserDto("mavi", "12345", "shima", "mousavi");
        userService.create(userDto);
        ChangePasswordDto passwordDto= new ChangePasswordDto("user","1234","123");
        userService.changePassword(passwordDto);
    }

    @Test(expected = NotFoundException.class)
public void getByIdNotFound(){
        UserDto userDto = new UserDto("mavi", "12345", "shima", "mousavi");
        userService.create(userDto);
        userService.getById(2l);
    }
}