package com.surena.interview.controller;

import com.surena.interview.model.UserDto;
import com.surena.interview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserRestController {
    @Autowired
    private IUserService iUserService;

    @PostMapping(value = "/")
    public ResponseEntity<UserDto> create(@RequestBody UserDto request) {
        return new ResponseEntity<>(iUserService.create(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/users/")
    public ResponseEntity<List<UserDto>> getAll(){
        return new ResponseEntity<>(iUserService.getAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
        return new ResponseEntity<>(iUserService.getById(id),HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userName}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String userName){
        return new ResponseEntity<>(iUserService.getByUsername(userName),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iUserService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:8080/api/users/2
    @DeleteMapping(value = "/user-name/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable String username){
        iUserService.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
