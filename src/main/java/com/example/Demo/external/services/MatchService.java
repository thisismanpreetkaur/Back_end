package com.example.Demo.external.services;

import com.example.Demo.entities.Match;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "MATCH-SERVICE")
public interface MatchService {

    //Feign client
    @GetMapping("/matches/all/{teamName}")
    List<Match> getMatches(@PathVariable String teamName);
}
