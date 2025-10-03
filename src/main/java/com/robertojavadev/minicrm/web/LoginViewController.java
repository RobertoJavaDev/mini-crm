package com.robertojavadev.minicrm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginViewController {

    @GetMapping
    public String loginView() {
        return "login";
    }
}
