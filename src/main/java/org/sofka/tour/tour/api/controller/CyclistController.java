package org.sofka.tour.tour.api.controller;

import org.sofka.tour.tour.domain.Cyclist;
import org.sofka.tour.tour.service.CyclistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Mono<Cyclist>> saveCyclist(@RequestBody Cyclist cyclist) {
        return new ResponseEntity<>(cyclistService.saveCyclist(cyclist), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Flux<Cyclist>> getAllCyclist(){
        return new ResponseEntity<>( cyclistService.getAllCyclists(), HttpStatus.OK);
    }

    @GetMapping("/{idCyclist}")
    public ResponseEntity<Mono<Cyclist>> getCyclistById(@PathVariable String idCyclist){
        return new ResponseEntity<>(cyclistService.getCyclistById(idCyclist),HttpStatus.OK);
    }

    @DeleteMapping("/{idCyclist}")
    public ResponseEntity<Mono<Void>> deleteCyclist(@PathVariable String idCyclist){

        return new ResponseEntity<>(cyclistService.deleteCyclist(idCyclist), HttpStatus.OK);
    }

    /**
     * EndPoint que permite consultar los ciclistas pertenecientes a un pais,
     * por medio del idCountry.
     *
     * @param idcountry del pais a ser consultado
     * @return flujo con la lista de ciclistas pertenecientes a ese pais.
     */
    @GetMapping("/searchbycountry/{idcountry}")
    public ResponseEntity<Flux<Cyclist>> getCyclistByCountry(@PathVariable String idcountry){
        return new ResponseEntity<>(cyclistService.getCyclistByCountry(idcountry), HttpStatus.OK);
    }

    /**
     * EndPoint que permite consultar todos los ciclistas de un
     * equipo, por medio del idTeam.
     *
     * @param idteam del equipo a ser consultado
     * @return flujo de datos con los ciclistas pertenecientes a ese equipo.
     */
    @GetMapping("/searchbyteam/{idteam}")
    public ResponseEntity<Flux<Cyclist>> getCyclistByTeam(@PathVariable String idteam){
        return new ResponseEntity<>(cyclistService.getCyclistByTeam(idteam), HttpStatus.OK);
    }

}
