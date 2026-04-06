package com.base.domain.usecase

import com.base.domain.repository.FavoritesRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : SuspendUseCase<Int, Unit>() {
    override suspend fun execute(parameters: Int) {
        favoritesRepository.removeFavorite(parameters)
    }
}
