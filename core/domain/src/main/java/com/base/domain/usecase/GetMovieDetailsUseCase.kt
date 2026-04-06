package com.base.domain.usecase

import com.base.domain.repository.MovieRepository
import com.base.model.MovieDetail
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : SuspendUseCase<Int, MovieDetail>() {
    override suspend fun execute(parameters: Int): MovieDetail {
        return movieRepository.getMovieDetails(movieId = parameters)
    }
}
