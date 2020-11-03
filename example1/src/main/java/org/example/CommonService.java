package org.example;

import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.model.Match;
import org.example.model.MatchDto;
import org.example.model.Team;
import org.example.model.TeamDto;
import org.example.model.TeamPageDto;
import org.example.repository.MatchRepository;
import org.example.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CommonService {

  private final EntityManager entityManager;
  private final MatchRepository matchRepository;
  private final TeamRepository teamRepository;

  @Transactional
  public void gen() {

    Team prevTeam = null;
    for (int i = 0; i < 2; i++) {

      var team = new Team();
      team.setName("t" + i);
      entityManager.persist(team);

      if (prevTeam != null) {
        for (int j = 0; j < 500; j++) {
          var match = new Match();
          match.setTeamAway(team);
          match.setTeamHome(prevTeam);
          entityManager.persist(match);
        }
      }

      prevTeam = team;
    }

    entityManager.flush();
  }

  public TeamPageDto get() {

    var teamMatches = matchRepository.getAllByTeam(1L).stream()
        .map(m -> {
          return MatchDto.builder()
              .away(TeamDto.builder().name(m.getTeamAway().getName()).build())
              .home(TeamDto.builder().name(m.getTeamHome().getName()).build())
              .build();
        })
        .collect(Collectors.toList());

    var team = teamRepository.getOne(1L);

    return TeamPageDto.builder()
        .teamDto(TeamDto.builder().name(team.getName()).build())
        .matches(teamMatches)
        .build();

  }
}
