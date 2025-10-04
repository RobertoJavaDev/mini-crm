package com.robertojavadev.minicrm.web;

import com.robertojavadev.minicrm.company.CompanyFacade;
import com.robertojavadev.minicrm.company.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String listCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Page<CompanyDto> companyPage = companyFacade.findAllCompanies(PageRequest.of(page, size));

        model.addAttribute("companies", companyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", companyPage.getTotalPages());

        return "companies/list";
    }
}
