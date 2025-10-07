package com.robertojavadev.minicrm.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginViewController {

    @GetMapping
    public String loginView(HttpServletRequest request, Model model) {
        Object message = request.getSession()
                .getAttribute("loginErrorMessage");
        if (message != null) {
            model.addAttribute("loginErrorMessage", message.toString());
            request.getSession().removeAttribute("loginErrorMessage");
        }
        return "login";
    }
}
