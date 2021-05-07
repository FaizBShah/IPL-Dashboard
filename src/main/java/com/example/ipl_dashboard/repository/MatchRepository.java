package com.example.ipl_dashboard.repository;

import com.example.ipl_dashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    /*
     This query is basically returning all
     the matches where
     Team1 == teamName1 && Team2 == teamName2
     sorted by date in descending order
    */
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    /*
     This query is basically returning all
     the matches where
     Team1 == teamName1 && Team2 == teamName2
     and date is between date1 and date2
     sorted by date in descending order
    */
    @Query("select m from Match m where " +
            "(m.team1 = :teamName or m.team2 = :teamName) " +
            "and m.date between :dateStart and :dateEnd order by date desc")
    List<Match> getMatchesByTeamBetweenDates(
            @Param("teamName") String teamName,
            @Param("dateStart") LocalDate dateStart,
            @Param("dateEnd") LocalDate dateEnd
    );

    /*
     List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
     String teamName1, LocalDate date1, LocalDate date2,
     String teamName2, LocalDate date3, LocalDate date4
     );
    */

    /*
     Can you believe this shit??
     You can create methods in interfaces in Java??
     Yes! Just add the default keyword, and done!!!
     Wow man.....just wow. Slow Claps...!!
    */
    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }

}
