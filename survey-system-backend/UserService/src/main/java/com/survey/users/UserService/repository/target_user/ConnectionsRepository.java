package com.survey.users.UserService.repository.target_user;


import com.survey.users.UserService.domain.target_user.Connection;
import com.survey.users.UserService.domain.target_user.TargetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionsRepository extends JpaRepository<Connection, Long> {

    List<Connection> findByUser(TargetUser user);

    @Query("SELECT cn FROM Connection cn WHERE cn.connector.surveyIdentifier IN (:surveyIds)")
    List<Connection> findBySurveyIds(@Param("surveyIds") List<String> surveyIds);
}
