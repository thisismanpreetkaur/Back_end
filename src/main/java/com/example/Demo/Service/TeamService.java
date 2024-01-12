package com.example.Demo.Service;

import com.example.Demo.entities.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    Team createTeam(Team team);
    Team updateTeam(Team team);
    Optional<Team> getTeam(int id);
    Optional<Team> getTeamByName(String name);
    List<Team> getAllTeam();
    Optional<Team> delTeam(int id);
}
