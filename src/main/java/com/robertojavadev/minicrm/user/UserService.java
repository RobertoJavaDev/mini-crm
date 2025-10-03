package com.robertojavadev.minicrm.user;

import com.robertojavadev.minicrm.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final String USER_WITH_EMAIL_DOES_NOT_EXIST = "User with email: %d does not exist";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    Set<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapUserToUserDto)
                .collect(Collectors.toSet());
    }

    public UserDto findByEmail(String email) {
        User user = findUserByEmail(email);
        return userMapper.mapUserToUserDto(user);
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_WITH_EMAIL_DOES_NOT_EXIST + email));
    }

    public void delete(UUID id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }
}
