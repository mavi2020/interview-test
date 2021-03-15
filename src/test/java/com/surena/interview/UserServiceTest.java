package com.surena.interview;

import com.surena.interview.dto.UserDto;
import com.surena.interview.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private RestTemplate restTemplate;


    @Test
    public void getMockingGetUser() {
        UserDto userDto = new UserDto();
        Mockito
                .when(restTemplate.getForEntity("http://localhost:8080/api/users/1", UserDto.class))
                .thenReturn(new ResponseEntity(userDto, HttpStatus.NOT_FOUND));

    }
}