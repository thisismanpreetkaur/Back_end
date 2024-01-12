package com.example.Demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Match Details")
public class Match {
    @Id
    int matchId;
    String secondTeam;
    String matchDuration;
    String official;
    String venue;
    String firstTeam;

}
