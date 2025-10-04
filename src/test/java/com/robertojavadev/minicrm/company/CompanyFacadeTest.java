package com.robertojavadev.minicrm.company;

import com.robertojavadev.minicrm.company.dto.CompanyAddDto;
import com.robertojavadev.minicrm.company.dto.CompanyDto;
import com.robertojavadev.minicrm.company.dto.CompanyUpdateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CompanyFacadeTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyFacade companyFacade;

    private CompanyAddDto companyAddDto;
    private CompanyUpdateDto companyUpdateDto;
    private CompanyDto companyDto;
    private UUID companyId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        companyId = UUID.randomUUID();

        companyAddDto = new CompanyAddDto(
                "Create Company",
                "create@test.com",
                "create-logo.png",
                "www.createCompanyTest.com");
        companyUpdateDto = new CompanyUpdateDto(
                "Updated Company",
                "updated@test.com",
                "update-logo.png",
                "www.updatedCompanyTest.com");
        companyDto = new CompanyDto(companyId,
                "Test Company",
                "test@test.com",
                "logo.png",
                "www.test.com");
    }

    @Test
    void shouldReturnCompanyWhenCreateCompanyIsCalledWithValidDto() {
        // given
        when(companyService.createCompany(companyAddDto)).thenReturn(companyDto);

        // when
        CompanyDto result = companyFacade.createCompany(companyAddDto);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(companyDto.id());
        assertThat(result.companyName()).isEqualTo(companyDto.companyName());
        verify(companyService, times(1)).createCompany(companyAddDto);
    }

    @Test
    void shouldReturnAllCompaniesWhenFindAllCompaniesIsCalled() {
        // given
        Pageable pageable = PageRequest.of(0, 5);
        Page<CompanyDto> companyPage = new PageImpl<>(List.of(companyDto), pageable, 1);

        when(companyService.findAllCompanies(pageable)).thenReturn(companyPage);

        // when
        Page<CompanyDto> result = companyFacade.findAllCompanies(pageable);

        // then
        assertThat(result.getContent())
                .hasSize(1)
                .first()
                .extracting(CompanyDto::id)
                .isEqualTo(companyDto.id());

        verify(companyService, times(1)).findAllCompanies(pageable);
    }

    @Test
    void shouldReturnCompanyWhenFindCompanyByIdIsCalledWithValidId() {
        // given
        when(companyService.findCompanyById(companyId)).thenReturn(companyDto);

        // when
        CompanyDto result = companyFacade.findCompanyById(companyId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(companyId);
        assertThat(result.companyName()).isEqualTo(companyDto.companyName());
        verify(companyService, times(1)).findCompanyById(companyId);
    }

    @Test
    void shouldReturnCompanyWhenUpdateCompanyIsCalledWithValidDtoAndId() {
        // given
        when(companyService.updateCompany(companyId, companyUpdateDto)).thenReturn(companyDto);

        // when
        CompanyDto result = companyFacade.updateCompany(companyId, companyUpdateDto);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(companyDto.id());
        assertThat(result.companyName()).isEqualTo(companyDto.companyName());
        verify(companyService, times(1)).updateCompany(companyId, companyUpdateDto);
    }

    @Test
    void shouldCallDeleteCompanyByIdWhenDeleteCompanyIsCalled() {
        // given
        doNothing().when(companyService).deleteCompanyById(companyId);

        // when
        companyFacade.deleteCompany(companyId);

        // then
        verify(companyService, times(1)).deleteCompanyById(companyId);
    }

    @Test
    void shouldThrowExceptionWhenUpdateCompanyIsCalledWithNullDto() {
        // when & then
        Assertions.assertThatThrownBy(() -> companyFacade.updateCompany(companyId, null))
                .isInstanceOf(NullPointerException.class);
    }
}
