package com.survey.users.UserService.repository.user;

import com.survey.users.UserService.domain.user.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("SELECT o FROM Organization o " +
            "WHERE (:name IS NULL OR o.name LIKE %:name%) AND " +
            "(:city IS NULL OR o.address.city LIKE %:city%) AND " +
            "(:country IS NULL OR o.address.country LIKE %:country%) AND " +
            "(:active IS NULL OR o.active=:active)")
    Page<Organization> findAllFiltered(@Param("name") String name, @Param("city") String city, @Param("country") String country, @Param("active") boolean active, Pageable pageable);
}
