package com.surena.interview.service;

import com.surena.interview.dto.ChangePasswordDto;
import com.surena.interview.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto create(UserDto request);

    void deleteByUsername(String userName);

    void deleteById(long id);

    UserDto update(long id, UserDto request);

    boolean changePassword(long id, ChangePasswordDto request);

    List<UserDto> getAll();

    UserDto getById(long id);

    UserDto getByUsername(String userName);
}
