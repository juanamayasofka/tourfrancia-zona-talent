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

        return cyclistService.deleteCyclist(idCyclist);
    }

    @GetMapping("/searchbycountry/{idcountry}")
    public Flux<Cyclist> getCyclistByCountry(@PathVariable String idcountry){
        return cyclistService.getCyclistByCountry(idcountry);
    }

    @GetMapping("/searchbyteam/{idteam}")
    public Flux<Cyclist> getCyclistByTeam(@PathVariable String idteam){
        return cyclistService.getCyclistByTeam(idteam);
    }

}
