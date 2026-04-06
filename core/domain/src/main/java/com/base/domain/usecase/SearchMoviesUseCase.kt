package com.base.domain.usecase

import com.base.domain.repository.MovieRepository
import com.base.model.Movie
import com.base.model.MovieResponse
import javax.inject.Inject

data class SearchMoviesParams(val query: String, val page: Int)

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : SuspendUseCase<SearchMoviesParams, MovieResponse<Movie>>() {
    override suspend fun execute(parameters: SearchMoviesParams): MovieResponse<Movie> {
        return movieRepository.searchMovies(query = parameters.query, page = parameters.page)
    }
}
