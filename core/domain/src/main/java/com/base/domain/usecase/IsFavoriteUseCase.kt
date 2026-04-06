package com.base.domain.usecase

import com.base.domain.repository.FavoritesRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : SuspendUseCase<Int, Boolean>() {
    override suspend fun execute(parameters: Int): Boolean {
        return favoritesRepository.isFavorite(parameters)
    }
}
