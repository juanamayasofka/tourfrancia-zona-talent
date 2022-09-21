package org.sofka.tour.tour.api.controller;

import org.sofka.tour.tour.domain.Country;
import org.sofka.tour.tour.domain.Cyclist;
import org.sofka.tour.tour.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/country")
public class CountryController {

    final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<Mono<Country>> saveCountry(@RequestBody Country country) {
        return new ResponseEntity(countryService.saveCountry(country), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Flux<Country>> getAllCountry(){
        return new ResponseEntity<>( countryService.getAllCountry(), HttpStatus.OK);
    }

    @GetMapping("/search/{idCountry}")
    public ResponseEntity<Mono<Country>> getCountryById(@PathVariable String idCountry){
        return new ResponseEntity<>( countryService.getCountryById(idCountry), HttpStatus.OK);
    }

    /**
     * Endpoint que permite eliminar las ciudades por id
     *
     * @param idCountry de la ciudad a ser eliminada
     * @return mono vacio.
     */
    @DeleteMapping("/{idCountry}")
    public ResponseEntity<Mono<Void>> deleteCountry(@PathVariable String idCountry){
        return new ResponseEntity<>(countryService.deleteCountry(idCountry), HttpStatus.OK);
    }

}
