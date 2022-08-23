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
        return Mono.just(cyclist).filter(cy -> Integer.parseInt(cy.getNumberCompetitor()) <= 3 )
                .filter(cyclist1 ->  cyclist1.getNumberCompetitor().chars().allMatch(Character::isDigit))
                .flatMap(cyclistRepository::save)
                .switchIfEmpty(Mono.empty());
    }

    public Flux<Cyclist> getAllCyclists(){
        return cyclistRepository.findAll();
    }

    public Mono<Cyclist> getCyclistById(String idCyclist){
        return cyclistRepository.findById(idCyclist);
    }

    public Mono<Void> deleteCyclist(Cyclist cyclist){
        return cyclistRepository.delete(cyclist);
    }
}
