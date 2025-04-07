package com.survey.users.UserService.mapper;

import com.survey.users.UserService.domain.user.Organization;
import com.survey.users.UserService.domain.user.Role;
import com.survey.users.UserService.domain.user.User;
import com.survey.users.UserService.domain.user.UserHasOrganization;
import com.survey.users.UserService.dto.user.UserCreateDto;
import com.survey.users.UserService.dto.user.UserDto;
import com.survey.users.UserService.dto.user.UserUpdateDto;
import com.survey.users.UserService.exception.NotFoundException;
import com.survey.users.UserService.repository.user.OrganizationRepository;
import com.survey.users.UserService.repository.user.RoleRepository;
import com.survey.users.UserService.repository.user.UserHasOrganizationRepository;
import com.survey.users.UserService.security.dto.UserAuthDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private RoleRepository roleRepository;
    private UserHasOrganizationRepository userHasOrganizationRepository;

    public User userCreateDtoToUser(UserCreateDto userCreateDto){
        User user = new User();
        Role role = roleRepository.findRoleByName(userCreateDto.getRole()).orElseThrow(() -> new NotFoundException(String.format("Role %s not found", userCreateDto.getRole())));
        List<UserHasOrganization> userHasOrganizationList = userHasOrganizationRepository.findUserHasOrganizationByUser(user);
        user.setRole(role);
        user.setEmail(userCreateDto.getEmail());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setOrganizations(userHasOrganizationList.stream()
                .map(UserHasOrganization::getOrganization)
                .collect(Collectors.toList()));

        return user;
    }

    public UserDto userToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());

        return userDto;
    }

    public User userUpdateDtoToUser(User user, UserUpdateDto userUpdateDto){
        user.setEmail(userUpdateDto.getEmail());
        user.setUsername(userUpdateDto.getUsername());
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());

        return user;
    }


    public UserAuthDetails mapToUserAuthDetails(User user) {
        return new UserAuthDetails(user.getEmail(), user.getPassword(), new ArrayList<>(), user.getRole().getName().name(), user.getOrganizations().stream()
                .map(Organization::getId)
                .collect(Collectors.toList()));
    }
}
