package com.robertojavadev.minicrm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crm")
public class DashboardViewController {

    @GetMapping()
    public String index() {
        return "index";
    }
}
