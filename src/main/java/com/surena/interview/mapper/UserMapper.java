package com.surena.interview.mapper;

import com.surena.interview.model.User;
import com.surena.interview.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User entity);

    User userDtoToUser(UserDto dto);
}
