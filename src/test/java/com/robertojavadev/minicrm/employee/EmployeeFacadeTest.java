package com.robertojavadev.minicrm.employee;

import com.robertojavadev.minicrm.company.Company;
import com.robertojavadev.minicrm.employee.dto.EmployeeAddDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeDto;
import com.robertojavadev.minicrm.employee.dto.EmployeeUpdateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeFacadeTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeFacade employeeFacade;

    private EmployeeAddDto employeeAddDto;
    private EmployeeUpdateDto employeeUpdateDto;
    private EmployeeDto employeeDto;
    private UUID employeeId;
    private Company company;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeId = UUID.randomUUID();

        company = new Company();
        company.setId(UUID.randomUUID());
        company.setCompanyName("Test Company");
        company.setEmail("company@test.com");
        company.setWebsite("www.company.com");
        company.setLogoFilename("logo.png");

        employeeAddDto = new EmployeeAddDto(
                "Jan",
                "Kowalski",
                company,
                "jan.kowalski@test.com",
                "+48123456789");

        employeeUpdateDto = new EmployeeUpdateDto(
                "Anna",
                "Kowalska",
                company,
                "anna.kowalska@test.com",
                "+48987654321");

        employeeDto = new EmployeeDto(
                employeeId,
                "Andrzej",
                "Zalewski",
                company,
                "andrzej.zalewski@test.com",
                "+48123456789");

    }

    @Test
    void shouldReturnEmployeeWhenCreateEmployeeIsCalledWithValidDto() {
        // given
        when(employeeService.createEmployee(employeeAddDto)).thenReturn(employeeDto);

        // when
        EmployeeDto result = employeeFacade.createEmployee(employeeAddDto);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(employeeDto.id());
        assertThat(result.firstName()).isEqualTo(employeeDto.firstName());
        assertThat(result.lastName()).isEqualTo(employeeDto.lastName());
        assertThat(result.company().getCompanyName()).isEqualTo("Test Company");
        verify(employeeService, times(1)).createEmployee(employeeAddDto);
    }

    @Test
    void shouldReturnAllEmployeesWhenFindAllEmployeesIsCalled() {
        // given
        when(employeeService.findAllEmployees()).thenReturn(List.of(employeeDto));

        // when
        List<EmployeeDto> result = employeeFacade.findAllEmployees();

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().id()).isEqualTo(employeeDto.id());
        assertThat(result.getFirst().company().getCompanyName()).isEqualTo("Test Company");
        verify(employeeService, times(1)).findAllEmployees();
    }

    @Test
    void shouldReturnEmployeeWhenFindEmployeeByIdIsCalledWithValidId() {
        // given
        when(employeeService.findEmployeeById(employeeId)).thenReturn(employeeDto);

        // when
        EmployeeDto result = employeeFacade.findEmployeeById(employeeId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(employeeId);
        assertThat(result.firstName()).isEqualTo(employeeDto.firstName());
        assertThat(result.company().getCompanyName()).isEqualTo("Test Company");
        verify(employeeService, times(1)).findEmployeeById(employeeId);
    }

    @Test
    void shouldReturnEmployeeWhenUpdateEmployeeIsCalledWithValidDtoAndId() {
        // given
        when(employeeService.updateEmployee(employeeId, employeeUpdateDto)).thenReturn(employeeDto);

        // when
        EmployeeDto result = employeeFacade.updateEmployee(employeeId, employeeUpdateDto);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(employeeDto.id());
        assertThat(result.firstName()).isEqualTo(employeeDto.firstName());
        verify(employeeService, times(1)).updateEmployee(employeeId, employeeUpdateDto);
    }

    @Test
    void shouldCallDeleteEmployeeByIdWhenDeleteEmployeeIsCalled() {
        // given
        doNothing().when(employeeService).deleteEmployeeById(employeeId);

        // when
        employeeFacade.deleteEmployee(employeeId);

        // then
        verify(employeeService, times(1)).deleteEmployeeById(employeeId);
    }

    @Test
    void shouldThrowExceptionWhenUpdateEmployeeIsCalledWithNullDto() {
        // when & then
        Assertions.assertThatThrownBy(() -> employeeFacade.updateEmployee(employeeId, null))
                .isInstanceOf(NullPointerException.class);
    }
}