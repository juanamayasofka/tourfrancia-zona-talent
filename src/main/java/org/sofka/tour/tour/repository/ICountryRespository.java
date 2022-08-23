package org.sofka.tour.tour.repository;

import org.sofka.tour.tour.domain.Country;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface ICountryRespository extends ReactiveMongoRepository<Country, String>,
        ReactiveQueryByExampleExecutor<Country> {
}
