package com.robertojavadev.minicrm.employee;

import com.robertojavadev.minicrm.employee.dto.EmployeeAddDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeUpdateDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    public static final String EMPLOYEE_WITH_ID_DOES_NOT_EXIST = "Employee with id: %s does not exist";
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto createEmployee(@NonNull @Valid EmployeeAddDto employeeAddDto) {
        Employee employee = employeeMapper.mapEmployeeAddDtoToEmployee(employeeAddDto);
        return employeeMapper.mapEmployeeToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public Page<EmployeeDto> findAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::mapEmployeeToEmployeeDto);
    }

    @Override
    public EmployeeDto findEmployeeById(@NonNull UUID id) {
        return employeeMapper.mapEmployeeToEmployeeDto(employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EMPLOYEE_WITH_ID_DOES_NOT_EXIST, id))));
    }

    @Override
    public EmployeeDto updateEmployee(@NonNull UUID id, @NonNull @Valid EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EMPLOYEE_WITH_ID_DOES_NOT_EXIST, id)));

        employee.setFirstName(employeeUpdateDto.firstName());
        employee.setLastName(employeeUpdateDto.lastName());
        employee.setCompany(employeeUpdateDto.company());
        employee.setEmail(employeeUpdateDto.email());
        employee.setPhone(employeeUpdateDto.phone());

        return employeeMapper.mapEmployeeToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployeeById(@NonNull UUID id) {
        employeeRepository.deleteById(id);
    }
}
