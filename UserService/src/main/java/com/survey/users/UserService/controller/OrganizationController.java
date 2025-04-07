package com.survey.users.UserService.controller;

import com.survey.users.UserService.dto.message.MessageDto;
import com.survey.users.UserService.dto.organization.*;
import com.survey.users.UserService.dto.user.UserDto;
import com.survey.users.UserService.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
@AllArgsConstructor
@CrossOrigin
public class OrganizationController {

    private OrganizationService organizationService;

    @GetMapping("/all")
    public ResponseEntity<Page<OrganizationDto>> getAll(Pageable pageable){
        return new ResponseEntity<>(organizationService.getAllOrg(pageable), HttpStatus.OK);
    }

    @GetMapping("/all/filter")
    public ResponseEntity<Page<OrganizationDto>> getAllFilter(@RequestBody OrganizationFilterDto organizationFilterDto, Pageable pageable){
        return new ResponseEntity<>(organizationService.getAllOrgFiltered(organizationFilterDto, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDetailedDto> get(@PathVariable Long id){
        return new ResponseEntity<>(organizationService.getOrganizationDetailed(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> post(@RequestBody OrganizationCreateDto organizationCreateDto){
        return new ResponseEntity<>(organizationService.addNewOrganization(organizationCreateDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrganizationDto> put(@RequestBody OrganizationUpdateDto organizationUpdateDto){
        return new ResponseEntity<>(organizationService.updateOrganization(organizationUpdateDto), HttpStatus.OK);
    }

    @PutMapping("/activity/{id}")
    public ResponseEntity<MessageDto> changeActivity(@PathVariable Long id) {
        return new ResponseEntity<>(organizationService.changeActivityForOrganization(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<Page<UserDto>> getUsersForOrg(@PathVariable Long id, Pageable pageable){
        return new ResponseEntity<>(organizationService.getUsersForOrganization(id, pageable), HttpStatus.OK);
    }

}
