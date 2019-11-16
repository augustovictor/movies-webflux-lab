package com.costavictor.movieswebfluxlab.movie

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux

@RunWith(SpringRunner::class)
@WebFluxTest(MovieController::class)
@ActiveProfiles("dev")
class MovieControllerTest {
    @MockBean
    private lateinit var movieService: MovieService

    @MockBean
    private lateinit var movieRepository: MovieRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `list movies - happy path`() {
        val movie = Movie(1, "This is the first movie", 2019,
                MovieGenre("MovieGender","Movie description"),
                mutableListOf(),
                mutableListOf()
        )

        val movieFlux = Flux.fromIterable(listOf(movie))
        `when`(movieService.findAll()).thenReturn(movieFlux)

        webTestClient.get().uri("/movies")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .json("""
                    [{
                        "id":1,
                        "title":"This is the first movie",
                        "year":2019,
                        "genre":{"name":"MovieGender","description":"Movie description"},
                        "ratings":[],
                        "cast":[]
                    }]
                """.trimIndent())

        verify(movieService, times(1)).findAll()


    }
}