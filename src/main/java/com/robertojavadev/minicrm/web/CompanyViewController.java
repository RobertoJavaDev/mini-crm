package com.robertojavadev.minicrm.web;

import com.robertojavadev.minicrm.company.CompanyFacade;
import com.robertojavadev.minicrm.company.dto.CompanyAddDto;
import com.robertojavadev.minicrm.company.dto.CompanyDto;
import com.robertojavadev.minicrm.company.dto.CompanyUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

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

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("company", new CompanyAddDto(
                "",
                "",
                "",
                ""));
        return "companies/add";
    }

    @PostMapping("/add")
    public String createCompany(@ModelAttribute("company") @Valid CompanyAddDto companyAddDto,
                                BindingResult result,
                                @RequestParam("logoFile") MultipartFile logoFile,
                                Model model) {

        if (result.hasErrors()) {
            return "companies/add";
        }

        String logoFilename = null;
        if (logoFile != null && !logoFile.isEmpty()) {
            try {
                logoFilename = companyFacade.uploadLogo(logoFile);
            } catch (IllegalArgumentException | IllegalStateException ex) {
                model.addAttribute("logoError", ex.getMessage());
                return "companies/add";
            }
        }

        CompanyAddDto companyToSave = new CompanyAddDto(
                companyAddDto.companyName(),
                companyAddDto.email(),
                logoFilename,
                companyAddDto.website()
        );

        companyFacade.createCompany(companyToSave);
        return "redirect:/companies/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        CompanyDto company = companyFacade.findCompanyById(id);
        model.addAttribute("company", company);
        return "companies/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCompany(@PathVariable UUID id,
                                @ModelAttribute("company") @Valid CompanyUpdateDto companyUpdateDto,
                                BindingResult result,
                                @RequestParam(value = "logoFile", required = false) MultipartFile logoFile,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "companies/edit";
        }

        CompanyDto existingCompany = companyFacade.findCompanyById(id);
        String logoFilename = existingCompany.logoFilename();

        if (logoFile != null && !logoFile.isEmpty()) {
            try {
                logoFilename = companyFacade.uploadLogo(logoFile);
            } catch (IllegalArgumentException | IllegalStateException ex) {
                model.addAttribute("logoError", ex.getMessage());
                model.addAttribute("id", id);
                return "companies/edit";
            }
        }

        CompanyUpdateDto updatedCompany = new CompanyUpdateDto(
                companyUpdateDto.companyName(),
                companyUpdateDto.email(),
                logoFilename,
                companyUpdateDto.website()
        );

        companyFacade.updateCompany(id, updatedCompany);
        return "redirect:/companies/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable UUID id) {
        companyFacade.deleteCompany(id);
        return "redirect:/companies/list";
    }
}
