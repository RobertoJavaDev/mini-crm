package com.robertojavadev.minicrm.web;

import com.robertojavadev.minicrm.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
class EmployeeViewController {
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String listEmployees() {
        return "employees/list";
    }
}
