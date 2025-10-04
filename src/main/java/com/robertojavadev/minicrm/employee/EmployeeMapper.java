package com.robertojavadev.minicrm.employee;

import com.robertojavadev.minicrm.employee.dto.EmployeeAddDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface EmployeeMapper {
    Employee mapEmployeeDtoToEmployee(EmployeeDto employeeDto);

    Employee mapEmployeeAddDtoToEmployee(EmployeeAddDto employeeAddDto);

    EmployeeDto mapEmployeeToEmployeeDto(Employee employee);
}
