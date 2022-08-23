package org.sofka.tour.tour.api.controller;

import org.sofka.tour.tour.domain.Cyclist;
import org.sofka.tour.tour.domain.Team;
import org.sofka.tour.tour.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/team")
public class TeamController {

    final
    TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @PostMapping
    public Mono<Team> saveTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @GetMapping
    public Flux<Team> getAllTeam() {
        return teamService.getAllTeam();
    }

    @GetMapping("/{idTeam}")
    public Mono<Team> getTeamtById(@PathVariable String idTeam) {
        return teamService.getTeamById(idTeam);
    }

    @DeleteMapping("/{idTeam}")
    public Mono<Void> deleteCyclist(@PathVariable String idTeam) {
        Team team = teamService.getTeamById(idTeam).block();
        return teamService.deleteTeam(team);
    }


}
