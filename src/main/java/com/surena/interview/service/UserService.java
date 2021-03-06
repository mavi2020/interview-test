package com.surena.interview.service;

import com.surena.interview.mapper.UserMapper;
import com.surena.interview.model.ChangePasswordDto;
import com.surena.interview.model.User;
import com.surena.interview.model.UserDto;
import com.surena.interview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
// comment one line -> Ctrl + /
// comment multiple lines -> Ctrl +Shift + /


    @Override
    public UserDto create(UserDto request) {
        User user = userMapper.userDtoToUser(request);
    /*    User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        //user.setCreateDate(new Date());
        user = userRepository.save(user);
        request.setId(user.getId());
        request.setCreateDate(user.getCreateDate());
        return request;*/
        return userMapper.userToUserDto(userRepository.save(user));
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
        Optional<User> byId = userRepository.findById(id);
        UserDto userDto = new UserDto();
        if (byId.isPresent()) {
            User user = userMapper.userDtoToUser(request);
            /*User user = new User();
            user.setUsername(request.getUsername());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());*/
            user.setId(id);
            /*user = userRepository.save(user);
            request.setId(id);
            request.setModifiedDate(user.getModifiedDate());*/
            return userMapper.userToUserDto(userRepository.save(user));
        } else {
            return null;
        }
    }

    @Override
    public boolean changePassword(long id, ChangePasswordDto request) {
        Optional<User> byId = userRepository.findByUsername(request.getUsername());
        if (byId.isPresent()) {
            User oldUser = byId.get();
            if (!oldUser.getPassword().equals(request.getOldPassword())) {
                return false;
            }
            oldUser.setPassword(request.getNewPassword());
            userRepository.save(oldUser);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDTOList = new ArrayList<>();
        UserDto userDTO;
        for (User user : userList) {

            userDTO = userMapper.userToUserDto(user);
            /*userDTO = new UserDto();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());*/
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public UserDto getById(long id) {
        Optional<User> byId = userRepository.findById(id);
        /*UserDto userDto = new UserDto();*/
        if (byId.isPresent()) {
            UserDto userDto = userMapper.userToUserDto(byId.get());
            /*User user = byId.get();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setCreateDate(user.getCreateDate());
            userDto.setModifiedDate(user.getModifiedDate());*/
            return userDto;
        } else {
            return null;
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
