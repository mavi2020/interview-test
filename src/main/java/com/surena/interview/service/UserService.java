package com.surena.interview.service;

import com.surena.interview.dto.ChangePasswordDto;
import com.surena.interview.dto.UserDto;
import com.surena.interview.exception.BadRequestException;
import com.surena.interview.exception.NotFoundException;
import com.surena.interview.mapper.UserMapper;
import com.surena.interview.model.User;
import com.surena.interview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
// comment one line -> Ctrl + /
// comment multiple lines -> Ctrl +Shift + /

    @Transactional
    @Override
    public UserDto create(UserDto request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadRequestException("Username is repetitive: " + request.getUsername());
        }
        //User user = userMapper.userDtoToUser(request);
        User user  = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Transactional
    @Override
    public void deleteByUsername(String userName) {
        if (userRepository.findByUsername(userName).isPresent()) {
            userRepository.deleteByUsername(userName);
        } else {
            throw new NotFoundException("username not found");
        }
    }

    @Transactional
    @Override
    public void deleteById(long id) {//Todo getById
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new NotFoundException("user id not found: " + id);
        }
    }

    @Transactional
    @Override
    public UserDto update(UserDto request) {
        Optional<User> optionalUser = userRepository.findById(request.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if ((!request.getFirstName().isEmpty() && !request.getFirstName().equals(user.getFirstName())) ||
                    (!request.getLastName().isEmpty() && !request.getLastName().equals(user.getLastName())) ||
                    (!request.getUsername().isEmpty() && !request.getUsername().equals(user.getPassword()))) {
                if (!request.getFirstName().isEmpty()) {
                    user.setFirstName(request.getFirstName());
                }
                if (!request.getLastName().isEmpty()) {
                    user.setLastName(request.getLastName());
                }
                if (!request.getUsername().isEmpty()) {
                    user.setUsername(request.getUsername());
                }
                user = userRepository.save(user);
                return userMapper.userToUserDto(user);
            } else {
                throw new BadRequestException("request data is not changed !");
            }
        } else {
            throw new NotFoundException("id: " + request.getId() + " Not Found.");
        }
    }

    @Override
    public boolean changePassword(ChangePasswordDto request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isPresent()) {
            User oldUser = userOptional.get();//user
            if (!oldUser.getPassword().equals(request.getOldPassword())) {
                throw new BadRequestException("old password is not correct");
            }
            oldUser.setPassword(request.getNewPassword());
            userRepository.save(oldUser);
            return true;
        } else {
            throw new NotFoundException("username not exist: " + request.getUsername());
        }
    }

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDTOList = userMapper.userToUserDto(userList);
        return userDTOList;
    }

    @Override
    public UserDto getById(long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return userMapper.userToUserDto(byId.get());
        } else {
            throw new NotFoundException("id not found: " + id);
        }
    }

    @Override
    public UserDto getByUsername(String userName) {
        Optional<User> optionalUserName = userRepository.findByUsername(userName);
        UserDto userDto = new UserDto();
        if (optionalUserName.isPresent()) {
            User byUserName = optionalUserName.get();
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
