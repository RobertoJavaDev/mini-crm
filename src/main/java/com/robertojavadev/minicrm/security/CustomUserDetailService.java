package com.robertojavadev.minicrm.security;

import com.robertojavadev.minicrm.user.UserService;
import com.robertojavadev.minicrm.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDto userDto = userService.findByEmail(email);
        return AppUser.builder()
                .userDto(userDto)
                .build();
    }
}
