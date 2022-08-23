package org.sofka.tour.tour.service;

import org.sofka.tour.tour.domain.Cyclist;
import org.sofka.tour.tour.domain.Team;
import org.sofka.tour.tour.repository.ITeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.text.html.parser.Entity;

@Service
public class TeamService {

    final ITeamRepository teamRepository;

    public TeamService(ITeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Mono<Team> saveTeam(Team team){
       return Mono.just(team).filter(team1 -> team1.getCodeTeam().length() <= 3)
               .flatMap(teamRepository::save)
               .switchIfEmpty(Mono.empty());
    }

    public Flux<Team> getAllTeam(){
        return teamRepository.findAll();
    }

    public Mono<Team> getTeamById(String idTeam){
        return teamRepository.findById(idTeam);
    }

    public Mono<Void> deleteTeam(Team team){
        return teamRepository.delete(team);
    }
}
