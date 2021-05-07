package com.example.ipl_dashboard.repository;

import com.example.ipl_dashboard.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {

    /*
     In JPA, you can create custom queries by
     specifying the method name.
     JPA ignores findBy, and then check the query
     This query is basically returning all
     the teams where team's name == teamName
    */
    Team findByTeamName(String teamName);

}
