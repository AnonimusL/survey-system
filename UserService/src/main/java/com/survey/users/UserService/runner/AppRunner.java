package com.survey.users.UserService.runner;

import com.survey.users.UserService.domain.target_user.TargetUser;
import com.survey.users.UserService.domain.user.*;
import com.survey.users.UserService.repository.target_user.TargetUserRepository;
import com.survey.users.UserService.repository.user.OrganizationRepository;
import com.survey.users.UserService.repository.user.RoleRepository;
import com.survey.users.UserService.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;

@Component
@AllArgsConstructor
public class AppRunner implements CommandLineRunner {

    private OrganizationRepository organizationRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private TargetUserRepository targetUserRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address();
        address.setCountry("Serbia");
        address.setCity("Belgrade");
        address.setStreet("Kneza Mihaila");
        address.setNumber("6");
        address.setPostcode("11000");

        Organization organization = new Organization();
        organization.setActive(true);
        organization.setName("Racunarski Fakultet - RAF");
        organization.setWebpage("https://raf.edu.rs");
        organization.setPhoneNumber("112233");
        organization.setAddress(address);
        organization.setAbout("An educational institution with a 20-year long tradition in teaching programming to young people");

        organizationRepository.save(organization);

        Role role = new Role();
        role.setName(RoleName.ROLE_ADMIN);
        role = roleRepository.save(role);

        User user = new User();
        user.setUsername("mijanaj");
        user.setEmail("jnajdic@raf.rs");
        String ep = passwordEncoder.encode("12345678");
        user.setPassword(ep);
        System.out.println(passwordEncoder.matches("12345678", ep));
        user.setRole(role);
        user.setFirstName("mija");
        user.setLastName("najdic");

        userRepository.save(user);




    }
}
