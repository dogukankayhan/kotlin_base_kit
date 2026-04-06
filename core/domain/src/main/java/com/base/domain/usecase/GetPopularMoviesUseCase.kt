package com.base.domain.usecase

import com.base.domain.repository.MovieRepository
import com.base.model.Movie
import com.base.model.MovieResponse
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : SuspendUseCase<Int, MovieResponse<Movie>>() {
    override suspend fun execute(parameters: Int): MovieResponse<Movie> {
        return movieRepository.getPopularMovies(page = parameters)
    }
}
