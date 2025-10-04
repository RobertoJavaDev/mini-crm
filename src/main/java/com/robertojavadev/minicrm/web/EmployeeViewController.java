package com.robertojavadev.minicrm.web;

import com.robertojavadev.minicrm.employee.EmployeeFacade;
import com.robertojavadev.minicrm.employee.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
class EmployeeViewController {
    private final EmployeeFacade employeeFacade;

    @GetMapping("/list")
    public String listEmployees() {
        return "employees/list";
    }
}
