package com.survey.users.UserService.service;

import com.survey.users.UserService.dto.message.MessageDto;
import com.survey.users.UserService.dto.user.UserCreateDto;
import com.survey.users.UserService.dto.user.UserDto;
import com.survey.users.UserService.dto.user.UserFilterDto;
import com.survey.users.UserService.dto.user.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<UserDto> getAllUsers(Pageable pageable);

    Page<UserDto> getAllUsersFiltered(UserFilterDto userFilterDto, Pageable pageable);

    UserDto getUser(String username);

    UserDto addNewUser(UserCreateDto userCreateDto);

    UserDto updateUser(UserUpdateDto userUpdateDto);

    MessageDto deleteUser(String username);

    List<String> getAllRoles();

}
