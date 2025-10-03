package com.robertojavadev.minicrm.user;

import com.robertojavadev.minicrm.user.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapUserToUserDto(UserDto userDto);
    UserDto mapUserToUserDto(User user);
}
