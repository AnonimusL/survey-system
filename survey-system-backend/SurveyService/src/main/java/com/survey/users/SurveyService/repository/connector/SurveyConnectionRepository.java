package com.survey.users.SurveyService.repository.connector;

import com.survey.users.SurveyService.domain.connector.SurveyConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Repository
public interface SurveyConnectionRepository extends JpaRepository<SurveyConnection, Long> {

    List<SurveyConnection> findByStudentGroupOrderBySurveySubject(String userGroup);

    List<SurveyConnection> findBySurveyTitle(String title);

    @Query("SELECT sc FROM SurveyConnection sc WHERE sc.id IN :titles")
    List<SurveyConnection> findBySurveyIds(List<Long> titles);

    List<SurveyConnection> findByStartTimeLessThanAndEndTimeGreaterThanAndScheduledIsFalse(Timestamp currentTimeStart, Timestamp currentTimeEnd);

    @Query("SELECT sc FROM SurveyConnection sc WHERE sc.survey = :name GROUP BY sc.surveySubject, sc.surveyTitle, sc.studentGroup ORDER BY sc.studentGroup")
    List<SurveyConnection> findDistinctSubjectTitle(@Param("name") String name);

    List<SurveyConnection> findByIdIn(Set<Long> ids);

    List<SurveyConnection> findByStudentGroupAndActivatedOrderBySurveySubject(String group, boolean activated);

}
