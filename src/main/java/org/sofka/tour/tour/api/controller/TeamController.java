package org.sofka.tour.tour.api.controller;

import org.sofka.tour.tour.domain.Cyclist;
import org.sofka.tour.tour.domain.Team;
import org.sofka.tour.tour.service.TeamService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Mono<Team>> saveTeam(@RequestBody Team team) {

        return new ResponseEntity<>(teamService.saveTeam(team), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Flux<Team>> getAllTeam() {
        return new ResponseEntity<>(teamService.getAllTeam(), HttpStatus.OK);
    }

    @GetMapping("/{idTeam}")
    public ResponseEntity<Mono<Team>> getTeamtById(@PathVariable String idTeam) {
        return new ResponseEntity<>(teamService.getTeamById(idTeam),HttpStatus.OK);
    }

    @DeleteMapping("/{idTeam}")
    public ResponseEntity<Mono<Void>> deleteCyclist(@PathVariable String idTeam) {
        return new ResponseEntity<>(teamService.deleteTeam(idTeam),HttpStatus.OK);
    }

    /**
     * EndPoint que permite buscar los equipos pertenecientes a un
     * pais por medio del idCountry.
     *
     * @param idContry criterio de busqueda.
     * @return flujo de teams.
     */
    @GetMapping("/searchbycountry/{idContry}")
    public ResponseEntity<Flux<Team>> getTeamtByCountry(@PathVariable String idContry) {
        return new ResponseEntity<>(teamService.getTeamByCountry(idContry), HttpStatus.OK);
    }
}
