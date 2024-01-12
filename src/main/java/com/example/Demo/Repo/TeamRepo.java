package com.example.Demo.Repo;

import com.example.Demo.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TeamRepo extends JpaRepository<Team, Integer> {
    Team findByName(String name);
}
