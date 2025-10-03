package com.robertojavadev.minicrm.user;

import com.robertojavadev.minicrm.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    Set<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapUserToUserDto)
                .collect(Collectors.toSet());
    }

    public void delete(UUID id) {
        userRepository.delete(id);
    }
}
