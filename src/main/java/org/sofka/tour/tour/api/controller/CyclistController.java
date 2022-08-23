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

    /**
     * EndPoint que permite consultar los ciclistas pertenecientes a un pais,
     * por medio del idCountry.
     *
     * @param idcountry del pais a ser consultado
     * @return flujo con la lista de ciclistas pertenecientes a ese pais.
     */
    @GetMapping("/searchbycountry/{idcountry}")
    public Flux<Cyclist> getCyclistByCountry(@PathVariable String idcountry){
        return cyclistService.getCyclistByCountry(idcountry);
    }

    /**
     * EndPoint que permite consultar todos los ciclistas de un
     * equipo, por medio del idTeam.
     *
     * @param idteam del equipo a ser consultado
     * @return flujo de datos con los ciclistas pertenecientes a ese equipo.
     */
    @GetMapping("/searchbyteam/{idteam}")
    public Flux<Cyclist> getCyclistByTeam(@PathVariable String idteam){
        return cyclistService.getCyclistByTeam(idteam);
    }

}
