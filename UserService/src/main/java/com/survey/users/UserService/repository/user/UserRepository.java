package com.survey.users.UserService.repository.user;

import com.survey.users.UserService.domain.user.RoleName;
import com.survey.users.UserService.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u " +
            "WHERE (:firstname IS NULL OR u.firstName LIKE %:firstname%) AND " +
            "(:lastname IS NULL OR u.lastName LIKE %:lastname%) AND " +
            "(:role IS NULL OR u.role.name=:role) AND " +
            "(:deleted IS NULL OR u.deleted=:deleted) AND " +
            "(:disabled IS NULL OR u.disabled=:disabled)")
    Page<User> findAllFiltered(@Param("firstname") String firstName, @Param("lastname") String lastName, @Param("deleted") boolean deleted, @Param("disabled") boolean disabled, @Param("role") RoleName role, Pageable pageable);

    Optional<User> findByEmail(String email);
}
