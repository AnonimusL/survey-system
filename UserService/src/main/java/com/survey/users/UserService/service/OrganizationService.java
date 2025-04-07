package com.survey.users.UserService.service;


import com.survey.users.UserService.dto.message.MessageDto;
import com.survey.users.UserService.dto.organization.*;
import com.survey.users.UserService.dto.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrganizationService {

    Page<OrganizationDto> getAllOrg(Pageable pageable);

    Page<OrganizationDto> getAllOrgFiltered(OrganizationFilterDto organizationFilterDto, Pageable pageable);

    Page<UserDto> getUsersForOrganization(Long id, Pageable pageable);

    OrganizationDto addNewOrganization(OrganizationCreateDto organizationCreateDto);

    OrganizationDto updateOrganization(OrganizationUpdateDto organizationUpdateDto);

    OrganizationDetailedDto getOrganizationDetailed(Long id);

    MessageDto changeActivityForOrganization(Long id);

}
