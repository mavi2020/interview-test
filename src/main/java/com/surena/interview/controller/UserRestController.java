package com.surena.interview.controller;

import com.surena.interview.dto.ChangePasswordDto;
import com.surena.interview.dto.UserDto;
import com.surena.interview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserRestController {
    @Autowired
    private IUserService iUserService;

    @PostMapping(value = "/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto request) {
        return new ResponseEntity<>(iUserService.create(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<UserDto> update(@RequestBody UserDto request) {
        return new ResponseEntity<>(iUserService.update(request), HttpStatus.OK);
    }

    @PutMapping(value = "/changePassword/{id}")//ToDo remove id
    public ResponseEntity<Boolean> changePassword(@PathVariable Long id, @RequestBody ChangePasswordDto request) {
        return new ResponseEntity<>(iUserService.changePassword(id, request), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllUsers/")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(iUserService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(iUserService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getByUserName/{userName}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String userName) {
        return new ResponseEntity<>(iUserService.getByUsername(userName), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iUserService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:8080/api/users/2
    @DeleteMapping(value = "/deleteByUserName/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {
        iUserService.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
