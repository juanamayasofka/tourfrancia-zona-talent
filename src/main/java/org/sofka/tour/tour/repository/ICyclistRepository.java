package org.sofka.tour.tour.repository;

import org.sofka.tour.tour.domain.Cyclist;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ICyclistRepository extends ReactiveMongoRepository<Cyclist, String >,
        ReactiveQueryByExampleExecutor<Cyclist> {


    Flux<Cyclist> findByIdCountry(String idCountry);

    Flux<Cyclist> findByIdTeam(String idConutry);

}
