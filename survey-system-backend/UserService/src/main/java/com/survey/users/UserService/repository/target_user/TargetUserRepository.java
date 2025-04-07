package com.survey.users.UserService.repository.target_user;

import com.survey.users.UserService.domain.target_user.TargetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TargetUserRepository extends JpaRepository<TargetUser, Long> {

    Optional<TargetUser> findByEmail(String email);

    @Query(value = "SELECT * FROM target_users tu " +
            "WHERE EXISTS (SELECT 1 FROM jsonb_each(tu.additional_data) AS kv(key, value) " +
            "WHERE kv.key = ANY(:keys) AND kv.value = ANY(:values))",
            nativeQuery = true)
    List<TargetUser> findByAdditionalData(@Param("keys") List<String> keys, @Param("values") List<String> values);
}