package com.survey.users.SurveyService.repository;

import com.survey.users.SurveyService.domain.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Page<Survey> findByOrganizationId(Long organization, Pageable pageable);

    Page<Survey> findByOrganizationIdAndUserId(Long id, Long idx, Pageable pageable);
}
