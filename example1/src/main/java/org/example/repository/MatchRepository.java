package org.example.repository;

import java.util.List;
import org.example.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

  @Query("select m from Match m where m.teamAway.id=:id or m.teamHome.id=:id")
  List<Match> getAllByTeam(@Param("id") long l);
}
