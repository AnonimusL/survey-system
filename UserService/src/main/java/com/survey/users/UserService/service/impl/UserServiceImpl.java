package com.survey.users.UserService.service.impl;

import com.survey.users.UserService.domain.target_user.TargetUser;
import com.survey.users.UserService.domain.user.*;
import com.survey.users.UserService.dto.message.MessageDto;
import com.survey.users.UserService.dto.user.UserCreateDto;
import com.survey.users.UserService.dto.user.UserDto;
import com.survey.users.UserService.dto.user.UserFilterDto;
import com.survey.users.UserService.dto.user.UserUpdateDto;
import com.survey.users.UserService.exception.NotFoundException;
import com.survey.users.UserService.mapper.UserMapper;
import com.survey.users.UserService.producer.RabbitMQProducer;
import com.survey.users.UserService.repository.user.OrganizationRepository;
import com.survey.users.UserService.repository.user.RoleRepository;
import com.survey.users.UserService.repository.user.UserHasOrganizationRepository;
import com.survey.users.UserService.repository.user.UserRepository;
import com.survey.users.UserService.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final UserHasOrganizationRepository userHasOrganizationRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private RabbitMQProducer rabbitMQProducer;

    public UserServiceImpl(UserRepository userRepository, OrganizationRepository organizationRepository,
                           UserHasOrganizationRepository userHasOrganizationRepository,
                           RoleRepository roleRepository, UserMapper userMapper, RabbitMQProducer rabbitMQProducer){
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.userHasOrganizationRepository = userHasOrganizationRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.rabbitMQProducer = rabbitMQProducer;
    }


    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public Page<UserDto> getAllUsersFiltered(UserFilterDto userFilterDto, Pageable pageable) {
        return userRepository.findAllFiltered(userFilterDto.getFirstName(), userFilterDto.getLastName(),
                                      userFilterDto.isDeleted(), userFilterDto.isDisabled(), RoleName.valueOf(userFilterDto.getRole()), pageable)
                                        .map(userMapper::userToUserDto);
    }

    @Override
    public UserDto getUser(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException(String.format("User with username %s not found", username)));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto addNewUser(UserCreateDto userCreateDto) {
        Organization organization = organizationRepository.findById(userCreateDto.getOrganization())
                .orElseThrow(() -> new NotFoundException("Organization not found"));

        User user = userRepository.findUserByEmail(userCreateDto.getEmail()).orElse(null);

        if (user == null) {
            user = userMapper.userCreateDtoToUser(userCreateDto);
            user = userRepository.save(user);
        }

        addUserToOrganization(user, organization);

        return userMapper.userToUserDto(user);
    }

    private void addUserToOrganization(User user, Organization organization) {
        UserHasOrganization userHasOrganization = userHasOrganizationRepository
                .findUserHasOrganizationByOrganizationAndUser(organization, user)
                .orElse(null);

        if (userHasOrganization == null) {
            userHasOrganization = new UserHasOrganization();
            userHasOrganization.setOrganization(organization);
            userHasOrganization.setUser(user);
            userHasOrganizationRepository.save(userHasOrganization);
        }
    }

    @Override
    public UserDto updateUser(UserUpdateDto userUpdateDto) {
        User user = userRepository.findUserByUsername(userUpdateDto.getOldUsername()).orElseThrow(() -> new RuntimeException(String.format("User with username %s not found", userUpdateDto.getOldUsername())));
        user = userMapper.userUpdateDtoToUser(user, userUpdateDto);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public MessageDto deleteUser(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException(String.format("User with username %s not found", username)));
        user.setDeleted(true);
        userRepository.save(user);
        return new MessageDto("User successfully deleted");
    }

    @Override
    public List<String> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(Role::getName)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

}
