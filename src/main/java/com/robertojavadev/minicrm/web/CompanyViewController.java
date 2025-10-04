package com.robertojavadev.minicrm.web;

import com.robertojavadev.minicrm.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyViewController {
    private final CompanyService companyService;

    @GetMapping("/list")
    public String listCompanies() {
        return "companies/list";
    }
}
