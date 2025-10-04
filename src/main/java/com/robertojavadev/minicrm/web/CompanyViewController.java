package com.robertojavadev.minicrm.web;

import com.robertojavadev.minicrm.company.CompanyFacade;
import com.robertojavadev.minicrm.company.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyViewController {
    private final CompanyFacade companyFacade;

    @GetMapping("/list")
    public String listCompanies(Model model) {
        List<CompanyDto> companies = companyFacade.findAllCompanies();
        model.addAttribute("companies", companies);
        return "companies/list";
    }
}
