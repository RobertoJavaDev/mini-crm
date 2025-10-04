package com.robertojavadev.minicrm.employee;

import com.robertojavadev.minicrm.employee.dto.EmployeeAddDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeUpdateDto;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface EmployeeService {
    EmployeeDto createEmployee(@NonNull @Valid EmployeeAddDto employeeAddDto);

    Page<EmployeeDto> findAllEmployees(Pageable pageable);

    EmployeeDto findEmployeeById(@NonNull UUID employeeId);

    EmployeeDto updateEmployee(@NonNull UUID employeeId, @NonNull @Valid EmployeeUpdateDto employeeUpdateDto);

    void deleteEmployeeById(@NonNull UUID employeeId);
}
