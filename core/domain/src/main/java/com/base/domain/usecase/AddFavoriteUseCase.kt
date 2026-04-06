package com.base.domain.usecase

import com.base.domain.repository.FavoritesRepository
import com.base.model.Movie
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : SuspendUseCase<Movie, Unit>() {
    override suspend fun execute(parameters: Movie) {
        favoritesRepository.addFavorite(parameters)
    }
}
