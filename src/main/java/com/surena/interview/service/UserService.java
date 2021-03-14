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
            throw new BadRequestException("Username is repetitive");
        }
        User user = userMapper.userDtoToUser(request);//ToDo unique UserName check

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
        return userMapper.userToUserDto(userRepository.save(user));//ToDo make 2 line clean code
    }


    @Transactional
    @Override
    public void deleteByUsername(String userName) {//ToDo getByUserName
        userRepository.deleteByUsername(userName);
    }

    @Transactional
    @Override
    public void deleteById(long id) {//Todo getById
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserDto update(long id, UserDto request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if ((!request.getFirstName().isEmpty() && !request.getFirstName().equals(user.getFirstName())) ||
                    !request.getLastName().isEmpty() ||
                    !request.getUsername().isEmpty()) {
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
                //todo : return exception handler !!!
                throw new BadRequestException("request data is not changed !");
            }
        } else {
            throw new NotFoundException("id: " + id);
        }
    }

    @Override
    public boolean changePassword(long id, ChangePasswordDto request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());//optionalUser
        if (userOptional.isPresent()) {
            User oldUser = userOptional.get();//user
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
        List<UserDto> userDTOList = new ArrayList<>(); //use in mapper userLIst , userDtoList
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
            return userMapper.userToUserDto(byId.get());
            /*User user = byId.get();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setCreateDate(user.getCreateDate());
            userDto.setModifiedDate(user.getModifiedDate());*/
//            return userDto;
        } else {//return exception
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
