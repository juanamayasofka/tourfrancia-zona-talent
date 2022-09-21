package org.sofka.tour.tour.service;

import org.sofka.tour.tour.domain.Country;
import org.sofka.tour.tour.domain.Cyclist;
import org.sofka.tour.tour.repository.ICountryRespository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CountryService {


    final ICountryRespository countryRespository;

    public CountryService(ICountryRespository countryRespository) {
        this.countryRespository = countryRespository;
    }

    public Mono<Country> saveCountry(Country country){
        return countryRespository.save(country);
    }

    public Flux<Country> getAllCountry(){
        return countryRespository.findAll();
    }

    public Mono<Country> getCountryById(String idCountry){
        return countryRespository.findById(idCountry);
    }

    public Mono<Void> deleteCountry(String country){

        return countryRespository.findById(country)
                .flatMap(countryRespository::delete);
    }
}
