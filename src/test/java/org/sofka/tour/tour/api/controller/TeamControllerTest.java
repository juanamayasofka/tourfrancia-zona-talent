package org.sofka.tour.tour.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sofka.tour.tour.domain.Country;
import org.sofka.tour.tour.domain.Team;
import org.sofka.tour.tour.repository.ITeamRepository;
import org.sofka.tour.tour.service.TeamService;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class TeamControllerTest {

    @Mock
    private TeamService teamService;

    private WebTestClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        TeamController router = new TeamController(teamService);

        this.client = WebTestClient.bindToController(router)
                .configureClient().baseUrl("/team").build();
    }

    @Test
    void saveTeamValidandoCodigoMayor3() {

        Country country = Country.builder()
                .code("Col")
                .id("456")
                .name("Colombia")
                .build();

        Team team = Team.builder()
                .codeTeam("123456789")
                .name("Movistar")
                .country(country)
                .build();

        teamService.saveTeam(any(Team.class));

        this.client.post().bodyValue(team).exchange()
                .expectStatus()
                .isOk()
                .expectBody(Team.class)
                .isEqualTo(null);


    }

    @Test
    void saveTeam() {

        Country country = Country.builder()
                .code("Col")
                .id("456")
                .name("Colombia")
                .build();

        Team team = Team.builder()
                .codeTeam("123")
                .name("Movistar")
                .country(country)
                .build();

        teamService.saveTeam(any(Team.class));

        this.client.post().bodyValue(team).exchange()
                .expectStatus()
                .isOk()
                .expectBody(Team.class)
                .isEqualTo(null);
    }

    @Test
    void getAllTeam() {
        Country country = Country.builder()
                .code("Col")
                .id("456")
                .name("Colombia")
                .build();

        Team team = Team.builder()
                .codeTeam("123")
                .name("Movistar")
                .country(country)
                .build();

        Team team2 = Team.builder()
                .codeTeam("321")
                .name("Astana")
                .country(country)
                .build();

        List<Team> list = new ArrayList<>();
        list.add(team);
        list.add(team2);

        when(teamService.getAllTeam()).thenReturn(Flux.fromStream(list.stream()));

        this.client.get().exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Team.class)
                .isEqualTo(list);
    }

    @Test
    void getTeamtById() {

        Country country = Country.builder()
                .code("Col")
                .id("456")
                .name("Colombia")
                .build();

        Team team = Team.builder()
                .codeTeam("123")
                .name("Movistar")
                .country(country)
                .build();

        when(teamService.getTeamById("123")).thenReturn(Mono.just(team));

        this.client.get().uri("/123").exchange()
                .expectStatus()
                .isOk()
                .expectBody(Team.class)
                .isEqualTo(team);

    }

    @Test
    void deleteCyclist() {
    }
}