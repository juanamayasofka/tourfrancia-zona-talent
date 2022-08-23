package org.sofka.tour.tour.api.controller;

import org.sofka.tour.tour.domain.Cyclist;
import org.sofka.tour.tour.service.CyclistService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/cyclist")
public class CyclistController {

    final CyclistService cyclistService;

    public CyclistController(CyclistService cyclistService) {
        this.cyclistService = cyclistService;
    }

    @PostMapping
    public Mono<Cyclist> saveCyclist(@RequestBody Cyclist cyclist) {
        return cyclistService.saveCyclist(cyclist);
    }

    @GetMapping
    public Flux<Cyclist> getAllCyclist(){
        return cyclistService.getAllCyclists();
    }

    @GetMapping("/{idCyclist}")
    public Mono<Cyclist> getCyclistById(@PathVariable String idCyclist){
        return cyclistService.getCyclistById(idCyclist);
    }

    @DeleteMapping("/{idCyclist}")
    public Mono<Void> deleteCyclist(@PathVariable String idCyclist){
        Cyclist cyclist = cyclistService.getCyclistById(idCyclist).block();
        return cyclistService.deleteCyclist(cyclist);
    }

}
