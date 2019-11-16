package com.costavictor.movieswebfluxlab.movie

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MovieService(private val movieRepository: MovieRepository) {
    fun findAll(): Flux<Movie> = movieRepository.findAll()

    fun save(movie: Movie) : Mono<Movie> = movieRepository.save(movie)
}