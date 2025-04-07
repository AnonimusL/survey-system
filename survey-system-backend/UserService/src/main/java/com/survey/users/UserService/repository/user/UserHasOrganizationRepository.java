package com.survey.users.UserService.repository.user;

import com.survey.users.UserService.domain.user.Organization;
import com.survey.users.UserService.domain.user.User;
import com.survey.users.UserService.domain.user.UserHasOrganization;
import com.survey.users.UserService.dto.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserHasOrganizationRepository extends JpaRepository<UserHasOrganization, Long> {

    Optional<UserHasOrganization> findUserHasOrganizationByOrganizationAndUser(Organization organization, User user);

    List<UserHasOrganization> findUserHasOrganizationByUser(User user);

    Page<User> findUserHasOrganizationByOrganization(Organization organization, Pageable pageable);
}
