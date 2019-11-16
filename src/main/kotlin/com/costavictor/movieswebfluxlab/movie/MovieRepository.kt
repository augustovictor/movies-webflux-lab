package com.costavictor.movieswebfluxlab.movie

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MovieRepository : ReactiveMongoRepository<Movie, Int>