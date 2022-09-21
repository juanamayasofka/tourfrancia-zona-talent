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

    /**
     * Metodo que permite guardar un registro de un team, con la regla de negocio
     * que los codigos no pueden ser mayores a 3. se hace un filter para que solo guarde
     * los registros que cumplen el criterio, switchIfEmpty en caso contrario retorna un
     * Mono vacio.
     *
     * @param team hacer almacenado en BD
     * @return Team si fue almacenado, Mono<void> si no guardo.
     */
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

    /**
     * Metodo que permite consultar un ciclista por id,
     *  y en el mismo flujo eliminarlo.
     *
     * @param idTeam hacer eliminado
     * @return Mono vacio
     */
    public Mono<Void> deleteTeam(String idTeam){
        return teamRepository.findById(idTeam)
                .flatMap(teamRepository::delete);
    }

    public Flux<Team> getTeamByCountry(String idCountry){
        return teamRepository.findByIdCountry(idCountry);
    }

}
