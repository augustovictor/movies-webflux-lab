package com.costavictor.movieswebfluxlab.movie

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.test.context.ActiveProfiles
import reactor.core.publisher.Mono

@RunWith(MockitoJUnitRunner::class)
@ActiveProfiles("dev")
class MovieServiceTest {
    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var subject: MovieService

    @Before
    fun setup() {
        subject = MovieService(movieRepository)
    }

    @Test
    fun `save - happy path`() {
        val expected = Movie(1, "This is the first movie", 2019,
                MovieGenre("MovieGender","Movie description"),
                mutableListOf(),
                mutableListOf()
        )

        `when`(movieRepository.save(expected)).thenReturn(Mono.just(expected))

        val actual = movieRepository.save(expected)

        actual.subscribe {
            Assert.assertEquals(1, it.id)
            Assert.assertEquals("This is the first movie", it.title)
            verify(movieRepository, times(1)).save(expected)
        }
    }
}