package com.costavictor.movieswebfluxlab.movie

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/movies")
class MovieController(private val movieService: MovieService) {
    @GetMapping
    fun getAll(): Flux<Movie> = movieService.findAll()
}