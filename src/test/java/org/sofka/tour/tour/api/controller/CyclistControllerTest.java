package org.sofka.tour.tour.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sofka.tour.tour.domain.Country;
import org.sofka.tour.tour.domain.Cyclist;
import org.sofka.tour.tour.domain.Team;
import org.sofka.tour.tour.repository.ICyclistRepository;
import org.sofka.tour.tour.service.CyclistService;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CyclistControllerTest {

    @Mock
    CyclistService cyclistService;

    private WebTestClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        CyclistController router = new CyclistController(cyclistService);

        this.client = WebTestClient.bindToController(router)
                .configureClient().baseUrl("/cyclist")
                .build();
    }

    @Test
    void saveCyclist() {

        Country country = Country.builder()
                .code("Col")
                .id("456")
                .name("Colombia")
                .build();

        Team team = Team.builder()
                .codeTeam("525")
                .name("Movistar")
                .idCountry("456")
                .build();

        Cyclist cyclist = Cyclist.builder()
                .numberCompetitor("123")
                .id("123")
                .name("Juan Amaya")
                .idCountry("456")
                .idTeam("525")
                .build();

       when(cyclistService.saveCyclist(any(Cyclist.class))).thenReturn(Mono.just(cyclist));

        this.client.post().bodyValue(cyclist).exchange()
                .expectStatus()
                .isOk()
                .expectBody(Cyclist.class)
                .isEqualTo(cyclist);
    }

    @Test
    void saveCyclistValidandoCodigoMenor3() {

        Country country = Country.builder()
                .code("Col")
                .id("456")
                .name("Colombia")
                .build();

        Team team = Team.builder()
                .codeTeam("123456789")
                .name("Movistar")
                .idCountry("456")
                .build();

        Cyclist cyclist = Cyclist.builder()
                .numberCompetitor("123456")
                .id("123")
                .name("Juan Amaya")
                .idCountry("456")
                .idTeam("123456789")
                .build();

        cyclistService.saveCyclist(any(Cyclist.class));

        this.client.post().bodyValue(cyclist).exchange()
                .expectStatus()
                .isOk()
                .expectBody(Cyclist.class)
                .isEqualTo(null);
    }

    @Test
    void getAllCyclist() {
        Country country = Country.builder()
                .code("Col")
                .id("456")
                .name("Colombia")
                .build();

        Team team = Team.builder()
                .codeTeam("123456789")
                .name("Movistar")
                .idCountry("456")
                .build();

        Cyclist cyclist = Cyclist.builder()
                .numberCompetitor("123")
                .id("123")
                .name("Juan Amaya")
                .idCountry("456")
                .idTeam("123456789")
                .build();

        Cyclist cyclist1 = Cyclist.builder()
                .numberCompetitor("321")
                .id("321")
                .name("Alejandra Vargas")
                .idCountry("456")
                .idTeam("123456789")
                .build();

        List<Cyclist> list = new ArrayList<>();
        list.add(cyclist);
        list.add(cyclist1);

        when(cyclistService.getAllCyclists()).thenReturn(Flux.fromStream(list.stream()));

        this.client.get().exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Cyclist.class)
                .isEqualTo(list);

    }

    @Test
    void getCyclistById() {
        Country country = Country.builder()
                .code("Col")
                .id("456")
                .name("Colombia")
                .build();

        Team team = Team.builder()
                .codeTeam("123456789")
                .name("Movistar")
                .idCountry("456")
                .build();

        Cyclist cyclist = Cyclist.builder()
                .numberCompetitor("123")
                .id("123")
                .name("Juan Amaya")
                .idCountry("456")
                .idTeam("123456789")
                .build();

        when(cyclistService.getCyclistById("123")).thenReturn(Mono.just(cyclist));

        this.client.get().uri("/123").exchange()
                .expectStatus()
                .isOk()
                .expectBody(Cyclist.class)
                .isEqualTo(cyclist);

    }
}