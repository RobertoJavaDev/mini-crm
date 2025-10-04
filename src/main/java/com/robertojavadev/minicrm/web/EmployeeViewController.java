package com.robertojavadev.minicrm.web;

import com.robertojavadev.minicrm.employee.EmployeeFacade;
import com.robertojavadev.minicrm.employee.EmployeeServiceImpl;
import com.robertojavadev.minicrm.employee.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
class EmployeeViewController {
    private final EmployeeFacade employeeFacade;

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
}
