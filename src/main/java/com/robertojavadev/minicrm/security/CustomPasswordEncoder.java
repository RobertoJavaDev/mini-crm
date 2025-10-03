package com.robertojavadev.minicrm.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class CustomPasswordEncoder extends BCryptPasswordEncoder {
}
