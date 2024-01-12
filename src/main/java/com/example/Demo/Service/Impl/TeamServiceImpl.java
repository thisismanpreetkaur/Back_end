package com.example.Demo.Service.Impl;

import com.example.Demo.Repo.TeamRepo;
import com.example.Demo.Service.TeamService;
import com.example.Demo.entities.Match;
import com.example.Demo.entities.Team;
import com.example.Demo.external.services.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MatchService matchService;

    private Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Override
    public Team createTeam(Team team) {
        return this.teamRepo.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
//        Optional<Team> team = teamRepo.findById(id);
        return this.teamRepo.save(team);
    }

    @Override
    public Optional<Team> getTeamByName(String name) {
        logger.info("Fetching team by team name");
        logger.info(name);
        Team team =(Team) teamRepo.findByName(name);
        logger.info("" , team);
        //Feign Client example for communicating microservices

        List<Match> list = matchService.getMatches(team.getName());
        logger.info("Fetched the list of matches");
        team.setMatch(list);

        return Optional.of(team);
    }

    @Override
    public Optional<Team> getTeam(int id) {
        Team team = teamRepo.findById(id).orElseThrow();

        //Rest Teamplate example to communicate with other microservices

//        Get Matches from here http://localhost:8091/matches/all/SFA Academy
//        ArrayList<Match> list = restTemplate.getForObject("http://MATCH-SERVICE/matches/all/" + team.getName(), ArrayList.class);
//        logger.info("Printing Array List of "+ team.getName() + " Matches");
//        logger.info("" + list);
//        System.out.println(list);

        //Feign Client example for communicating microservices

        List<Match> list = matchService.getMatches(team.getName());
        logger.info("Fetched the list of matches");
        team.setMatch(list);
        return Optional.of(team);

    }

    @Override
    public List<Team> getAllTeam() {
        return this.teamRepo.findAll();
    }

    @Override
    public Optional<Team> delTeam(int id) {
        this.teamRepo.deleteById(id);
        return teamRepo.findById(id);
    }
}
