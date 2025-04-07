package com.survey.users.UserService.mapper;

import com.survey.users.UserService.domain.user.Organization;
import com.survey.users.UserService.domain.user.Address;
import com.survey.users.UserService.dto.organization.OrganizationCreateDto;
import com.survey.users.UserService.dto.organization.OrganizationDetailedDto;
import com.survey.users.UserService.dto.organization.OrganizationDto;
import com.survey.users.UserService.dto.organization.OrganizationUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public Organization organizationCreateDtoToOrganization(OrganizationCreateDto organizationCreateDto){
        Organization organization = new Organization();
        organization.setName(organizationCreateDto.getName());
        organization.setLogo(organizationCreateDto.getLogo());
        organization.setActive(true);
        organization.setEmailFormat(organizationCreateDto.getEmailFormat());
        organization.setWebpage(organizationCreateDto.getWebpage());
        organization.setPhoneNumber(organizationCreateDto.getPhoneNumber());

        Address address = addressDtoToAddress(organizationCreateDto.getAddress());
        organization.setAddress(address);

        return organization;
    }

    public Organization organizationUpdateDtoToOrganization(Organization organization, OrganizationUpdateDto organizationUpdateDto){
        organization.setName(organizationUpdateDto.getName());
        organization.setLogo(organizationUpdateDto.getLogo());
        organization.setEmailFormat(organizationUpdateDto.getEmailFormat());
        organization.setWebpage(organizationUpdateDto.getWebpage());
        organization.setPhoneNumber(organizationUpdateDto.getPhoneNumber());

        Address address = addressDtoToAddress(organizationUpdateDto.getAddressDto());
        organization.setAddress(address);

        return organization;
    }

    public OrganizationDto organizationToOrganizationDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setName(organizationDto.getName());
        organizationDto.setCity(organization.getAddress().getCity());
        organizationDto.setCountry(organization.getAddress().getCountry());

        return organizationDto;
    }

    public OrganizationDetailedDto organizationToOrganizationDetailedDto(Organization organization){
        OrganizationDetailedDto organizationDetailedDto = new OrganizationDetailedDto();
        organizationDetailedDto.setName(organization.getName());
        organizationDetailedDto.setWebpage(organization.getWebpage());
        organizationDetailedDto.setPhoneNumber(organization.getPhoneNumber());
        organizationDetailedDto.setActive(organization.isActive());
        organizationDetailedDto.setAbout(organization.getAbout());

        OrganizationCreateDto.AddressDto addressDto = addressToAddressDto(organization.getAddress());
        organizationDetailedDto.setAddressDto(addressDto);

        return organizationDetailedDto;
    }

    private OrganizationCreateDto.AddressDto addressToAddressDto(Address address){
        OrganizationCreateDto.AddressDto addressDto = new OrganizationCreateDto.AddressDto();
        addressDto.setCountry(address.getCountry());
        addressDto.setCity(address.getCity());
        addressDto.setPostcode(address.getPostcode());
        addressDto.setStreet(address.getStreet());
        addressDto.setNumber(address.getNumber());

        return addressDto;
    }

    private Address addressDtoToAddress(OrganizationCreateDto.AddressDto addressDto){
        Address address = new Address();
        address.setCountry(addressDto.getCountry());
        address.setCity(addressDto.getCity());
        address.setPostcode(addressDto.getPostcode());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());

        return address;
    }
}
