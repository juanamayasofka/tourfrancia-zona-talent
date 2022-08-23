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
