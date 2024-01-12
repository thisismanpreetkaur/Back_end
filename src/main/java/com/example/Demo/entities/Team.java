package com.example.Demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "Team_Data")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    int id;

    String name;
    String players;
    String coach;
    String captain;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String payment;

    @Transient
    private List<Match> match = new ArrayList<>();
}
