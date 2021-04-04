package com.surena.interview;

import com.surena.interview.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getMockingGetUser() {
        UserDto userDto = new UserDto();
        Mockito
                .when(restTemplate.getForEntity("http://localhost:8080/api/users/getById/1", UserDto.class))
                .thenReturn(new ResponseEntity(userDto, HttpStatus.NOT_FOUND));

    }
}