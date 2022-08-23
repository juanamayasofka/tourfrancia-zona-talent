package org.sofka.tour.tour.repository;

import org.sofka.tour.tour.domain.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Flux;

public interface ITeamRepository  extends ReactiveMongoRepository<Team, String >,
        ReactiveQueryByExampleExecutor<Team> {
    Flux<Team> findByIdCountry(String idCountry);

}
