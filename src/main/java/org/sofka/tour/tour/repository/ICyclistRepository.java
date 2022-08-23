package org.sofka.tour.tour.repository;

import org.sofka.tour.tour.domain.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICyclistRepository extends ReactiveMongoRepository<Cyclist, String >,
        ReactiveQueryByExampleExecutor<Cyclist> {
}
