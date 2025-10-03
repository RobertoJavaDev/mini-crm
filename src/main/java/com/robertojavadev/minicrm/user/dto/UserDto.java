package com.robertojavadev.minicrm.user.dto;

import com.robertojavadev.minicrm.user.Role;

import java.util.UUID;

public record UserDto(UUID id,
                      String firstName,
                      String lastName,
                      String password,
                      String email,
                      String role) {
}
