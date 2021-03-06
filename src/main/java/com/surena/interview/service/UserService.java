package com.surena.interview.service;

import com.surena.interview.model.ChangePasswordDto;
import com.surena.interview.model.User;
import com.surena.interview.model.UserDto;
import com.surena.interview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
        userRepository.deleteByUsername(userName);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
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
        List<User> userList = userRepository.findAll();
        List<UserDto> userDTOList = new ArrayList<>();
        UserDto userDTO;
        for (User user : userList) {
            userDTO = new UserDto();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public UserDto getById(long id) {
        Optional<User> byId = userRepository.findById(id);
        UserDto userDto = new UserDto();
        if (byId.isPresent()) {
            User user = byId.get();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setCreateDate(user.getCreateDate());
            userDto.setModifiedDate(user.getModifiedDate());
            return userDto;
        } else {
            return null;
        }
    }

    @Override
    public UserDto getByUsername(String userName) {
        User byUserName = userRepository.findByUsername(userName);
        UserDto userDto = new UserDto();
        if (byUserName != null) {
            userDto.setId(byUserName.getId());
            userDto.setUsername(byUserName.getUsername());
            userDto.setFirstName(byUserName.getFirstName());
            userDto.setLastName(byUserName.getLastName());
            userDto.setCreateDate(byUserName.getCreateDate());
            userDto.setModifiedDate(byUserName.getModifiedDate());
        }
        return userDto;
    }
}
