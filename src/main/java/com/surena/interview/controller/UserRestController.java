package com.surena.interview.controller;

import com.surena.interview.model.UserDto;
import com.surena.interview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserRestController {
    @Autowired
    private IUserService iUserService;

    @PostMapping(value = "/")
    public ResponseEntity<UserDto> create(@RequestBody UserDto request) {
        return new ResponseEntity<>(iUserService.create(request), HttpStatus.CREATED);
    }
}
