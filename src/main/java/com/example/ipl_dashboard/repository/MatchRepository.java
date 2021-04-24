package com.example.ipl_dashboard.repository;

import com.example.ipl_dashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    // This query is basically returning all
    // the matches where
    // Team1 == teamName1 && Team2 == teamName2
    // sorted by date in descending order
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    // Can you believe this shit??
    // You can create methods in interfaces in Java??
    // Yes! Just add the default keyword, and done!!!
    // Wow man.....just wow. Slow Claps...!!
    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }

}
