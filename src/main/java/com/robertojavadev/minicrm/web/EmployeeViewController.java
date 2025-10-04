package com.robertojavadev.minicrm.web;

import com.robertojavadev.minicrm.company.CompanyFacade;
import com.robertojavadev.minicrm.company.dto.CompanyDto;
import com.robertojavadev.minicrm.employee.EmployeeFacade;
import com.robertojavadev.minicrm.employee.EmployeeServiceImpl;
import com.robertojavadev.minicrm.employee.dto.EmployeeAddDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
class EmployeeViewController {
    private final EmployeeFacade employeeFacade;
    private final CompanyFacade companyFacade;

    @GetMapping("/list")
    public String listEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Page<EmployeeDto> employeePage = employeeFacade.findAllEmployees(PageRequest.of(page, size));

        model.addAttribute("employees", employeePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());

        return "employees/list";
    }

    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employeeAddDto", new EmployeeAddDto(null, null, null, null, null));
        model.addAttribute("companies", companyFacade.findAllCompanies(PageRequest.of(0, 100)).getContent());
        return "employees/add";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute("employeeAddDto") EmployeeAddDto employeeAddDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("companies", companyFacade.findAllCompanies(PageRequest.of(0, 100)).getContent());
            return "employees/add";
        }

        employeeFacade.createEmployee(employeeAddDto);
        return "redirect:/employees/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        EmployeeDto employee = employeeFacade.findEmployeeById(id);
        List<CompanyDto> companies = companyFacade.findAllCompanies(PageRequest.of(0, 100)).getContent();
        model.addAttribute("employee", employee);
        model.addAttribute("companies", companies);
        return "employees/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable UUID id, @ModelAttribute @Validated EmployeeUpdateDto employeeUpdateDto) {
        employeeFacade.updateEmployee(id, employeeUpdateDto);
        return "redirect:/employees/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable UUID id) {
        employeeFacade.deleteEmployee(id);
        return "redirect:/employees/list";
    }
}
