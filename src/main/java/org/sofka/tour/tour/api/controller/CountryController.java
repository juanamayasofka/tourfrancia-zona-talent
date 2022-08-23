package org.sofka.tour.tour.api.controller;

import org.sofka.tour.tour.domain.Country;
import org.sofka.tour.tour.domain.Cyclist;
import org.sofka.tour.tour.service.CountryService;
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
    public Mono<Country> saveCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @GetMapping
    public Flux<Country> getAllCountry(){
        return countryService.getAllCountry();
    }

    @GetMapping("/search/{idCountry}")
    public Mono<Country> getCountryById(@PathVariable String idCountry){
        return countryService.getCountryById(idCountry);
    }

    /**
     * Endpoint que permite eliminar las ciudades por id
     *
     * @param idCountry de la ciudad a ser eliminada
     * @return mono vacio.
     */
    @DeleteMapping("/{idCountry}")
    public Mono<Void> deleteCountry(@PathVariable String idCountry){
        return countryService.deleteCountry(idCountry);
    }

}
