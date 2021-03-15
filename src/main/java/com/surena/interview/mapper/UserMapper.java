package com.surena.interview.mapper;

import com.surena.interview.model.User;
import com.surena.interview.dto.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
//@Component
public interface UserMapper {

    UserDto userToUserDto(User entity);

    User userDtoToUser(UserDto dto);

    List<UserDto> userToUserDto(List<User> users);

    List<User> userDtoToUser(List<UserDto> usersDto);

}
