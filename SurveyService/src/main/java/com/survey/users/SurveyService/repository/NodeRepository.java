package com.survey.users.SurveyService.repository;

import com.survey.users.SurveyService.domain.Domain;
import com.survey.users.SurveyService.domain.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {

    @Query("SELECT n FROM Node n WHERE n.organizationId = :organization AND n.domain = :domain AND " +
            "(n.userId = :user OR n.priv = false)")
    List<Node> findWorkspace(@Param("user") Long userId,
                             @Param("organization") Long organizationId,
                             @Param("domain")Domain domain);

    @Query("SELECT n FROM Node n WHERE n.organizationId = :organization AND " +
            "(n.userId = :user OR n.priv = false) AND " +
            "n.domain.name LIKE %:domain%")
    List<Node> findWorkspaceForDomain(@Param("user") Long userId, @Param("organization") Long organizationId, @Param("domain") String domain);

    Optional<Node> findByOrganizationIdAndDomainAndName(Long organizarionId, Domain domain, String name);
}
