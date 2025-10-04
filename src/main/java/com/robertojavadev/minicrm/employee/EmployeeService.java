package com.robertojavadev.minicrm.employee;

import com.robertojavadev.minicrm.employee.dto.EmployeeAddDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

//    public EmployeeDto createEmployee(EmployeeAddDto employeeAddDto) {
//        return employeeRepository.save(employeeAddDto);
//    }
//
//    public EmployeeDto getEmployeeById(UUID id) {
//        return employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found")));
//    }
//
//    public List<EmployeeDto> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    public EmployeeDto updateEmployee(UUID id) {
//        return employeeRepository.findById(id);
//    }

    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }
}
