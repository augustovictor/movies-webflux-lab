package com.costavictor.movieswebfluxlab.movie

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface UserRepository : ReactiveMongoRepository<User, Int> {
        fun findByUsername(username: String): Flux<User>
}