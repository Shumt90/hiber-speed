package org.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Match {

  @Id
  @GeneratedValue()
  private Long id;

  @ManyToOne
  private Team teamHome;

  @ManyToOne
  private Team teamAway;
}
