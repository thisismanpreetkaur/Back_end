package com.example.Demo.Controllers;

import com.example.Demo.Service.TeamService;
import com.example.Demo.entities.Team;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Teams")
public class TeamController {
    @Autowired
    private TeamService teamService;


    private Logger logger = LoggerFactory.getLogger(TeamController.class);

    //Create a Team
    @PostMapping("/register")
    public ResponseEntity<Team> createTeam(@RequestBody Team team){
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.createTeam(team));
    }

    //Get single Team
    @GetMapping("/{id}")
    @CircuitBreaker(name = "MatchTeam_CircuitBreaker", fallbackMethod = "MatchTeamFallback")
    public ResponseEntity<Optional<Team>> getTeam(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeam(id));
    }

    @GetMapping("/name/{name}")
    @CircuitBreaker(name = "MatchTeam_CircuitBreaker", fallbackMethod = "matchTeamFallback")
    public ResponseEntity<Optional<Team>> getByName(@PathVariable String name){
        Optional<Team> team = teamService.getTeamByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    // Creating Fallback Method for Circuit Breaker
    public ResponseEntity<Optional<Team>> matchTeamFallback(String userid, Exception ex){
        logger.info("Fallback is executed because service is down : "+ ex.getMessage());
        Optional<Team> team = Optional.ofNullable(Team.builder()
                .name("This is Dummy Team")
                .captain("Dummy")
                .id(100001)
                .build());
        return new ResponseEntity<>(team,HttpStatus.OK);
    }

    //Get all teams
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeam(){

        return ResponseEntity.status(HttpStatus.OK).body(teamService.getAllTeam());
    }

    //Delete a team
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Team>> delTeam(@PathVariable Integer id){

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(teamService.delTeam(id));
    }

    //Update Team Data
    @PutMapping("/update")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team){
        return ResponseEntity.status(HttpStatus.OK).body(teamService.updateTeam(team));
    }
}
