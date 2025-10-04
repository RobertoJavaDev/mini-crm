package com.robertojavadev.minicrm.employee;

import com.robertojavadev.minicrm.employee.dto.EmployeeAddDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeUpdateDto;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Component
@Validated
@RequiredArgsConstructor
public class EmployeeFacade {
    private final EmployeeService employeeService;

    public EmployeeDto createEmployee(@NonNull @Valid EmployeeAddDto employeeAddDto) {
        return employeeService.createEmployee(employeeAddDto);
    }

    public List<EmployeeDto> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    public EmployeeDto findEmployeeById(@NonNull UUID employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    public EmployeeDto updateEmployee(@NonNull UUID employeeId, @NonNull @Valid EmployeeUpdateDto employeeUpdateDto) {
        return employeeService.updateEmployee(employeeId, employeeUpdateDto);
    }

    public void deleteEmployee(@NonNull UUID employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }
}
