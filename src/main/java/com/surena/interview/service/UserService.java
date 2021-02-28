package com.surena.interview.service;

import com.surena.interview.model.ChangePasswordDto;
import com.surena.interview.model.User;
import com.surena.interview.model.UserDto;
import com.surena.interview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto create(UserDto request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setCreateDate(new Date());
        user = userRepository.save(user);
        request.setId(user.getId());
        return request;
    }

    @Override
    public void deleteByUsername(String userName) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public UserDto update(long id, UserDto request) {
        return null;
    }

    @Override
    public boolean changePassword(long id, ChangePasswordDto request) {
        return false;
    }

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto getById(long id) {
        return null;
    }

    @Override
    public UserDto getByUsername(String userName) {
        return null;
    }
}
