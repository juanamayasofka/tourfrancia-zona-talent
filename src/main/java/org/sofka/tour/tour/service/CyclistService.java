package org.sofka.tour.tour.service;


import org.sofka.tour.tour.domain.Cyclist;
import org.sofka.tour.tour.repository.ICyclistRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CyclistService {

    final ICyclistRepository cyclistRepository;

    public CyclistService(ICyclistRepository cyclistRepository) {
        this.cyclistRepository = cyclistRepository;
    }

    /**
     * Metodo que permite guardar un registro de un ciclista, con la regla de negocio
     * que los codigos no pueden ser mayores a 3. se hace un filter para que solo guarde
     * los registros que cumplen el criterio, switchIfEmpty en caso contrario retorna un
     * Mono vacio.
     *
     * @param cyclist hacer almacenado en BD
     * @return Cyclist si fue almacenado, Mono<void> si no guardo.
     */
    public Mono<Cyclist> saveCyclist(Cyclist cyclist){
        return Mono.just(cyclist)
                .filter(cy -> cy.getId().length() <= 3 )
                .filter(cyclist1 ->  cyclist1.getId().chars().allMatch(Character::isDigit))
                .flatMap(cyclistRepository::save)
                .switchIfEmpty(Mono.empty());
    }

    public Flux<Cyclist> getAllCyclists(){
        return cyclistRepository.findAll();
    }

    public Mono<Cyclist> getCyclistById(String idCyclist){
        return cyclistRepository.findById(idCyclist);
    }

    /**
     * Metodo que permite consultar un ciclista por id,
     * y en el mismo flujo eliminarlo.
     *
     * @param idCyclist hacer eliminado
     * @return Mono vacio.
     */
    public Mono<Void> deleteCyclist(String  idCyclist){
        return cyclistRepository.
                findById(idCyclist).
                flatMap(cyclistRepository::delete);

    }

    public Flux<Cyclist> getCyclistByCountry(String idConutry){
        return cyclistRepository.findByIdCountry(idConutry);
    }

    public Flux<Cyclist> getCyclistByTeam(String idConutry){
        return cyclistRepository.findByIdTeam(idConutry);
    }
}
