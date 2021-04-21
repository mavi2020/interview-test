package com.surena.interview.controller;

import com.surena.interview.dto.ChangePasswordDto;
import com.surena.interview.dto.UserDto;
import com.surena.interview.exception.BadRequestException;
import com.surena.interview.exception.NotFoundException;
import com.surena.interview.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserRestController userRestController;

    @BeforeEach
    public void insert() {
        UserDto userDto = new UserDto("mavi", "12345", "shima", "Mousavi");
        userRestController.create(userDto);
    }

    @Transactional
    @Test
    public void createTest() {
        UserDto userDto = new UserDto("a", "123", "C", "X");
        UserDto userDto1 = userRestController.create(userDto).getBody();
        Assert.assertEquals("a", userDto1.getUsername());
    }

    @Transactional
    @Test
    public void updateTest() {
        UserDto userDto = new UserDto("mavi", "1", "Armita", "sadeghi");
        UserDto userDto1 = userRestController.update(userDto).getBody();
        Assert.assertEquals("Armita", userDto1.getFirstName());
    }

    @Transactional
    @Test
    public void changePasswordTest(ChangePasswordDto request) {
        ChangePasswordDto userPasswordDto = new ChangePasswordDto("a", "123", "10");
        userRestController.changePassword(userPasswordDto);
        /*System.out.println(body);
        Assert.assertEquals(true,body);*/
    }

    @Transactional
    @Test
    public void deleteByUsernameTest() {
        ResponseEntity responseEntity = userRestController.deleteByUsername("mavi");
        Assert.assertNull(responseEntity.getBody());
    }

    @Transactional
    @Test
    public void deleteByIdTest() {
        ResponseEntity response = userRestController.delete(1L);
        Assert.assertEquals(new ResponseEntity(HttpStatus.OK),response);
    }


    @Transactional
    @Test
    public void getAllTest() {
        List<UserDto> userDtoList = userRestController.getAll().getBody();
        Assert.assertEquals(1,userDtoList.size());
    }

    @Transactional
    @Test
    public void getByIdTest() {
        UserDto userDto = userRestController.getById(1l).getBody();
        Assert.assertEquals("mavi",userDto.getUsername());
    }

    @Transactional
    @Test
    public void getByUsernameTest() {
        UserDto userDto = userRestController.getByUsername("mavi").getBody();
        Assert.assertEquals("shima",userDto.getFirstName());
    }
}
