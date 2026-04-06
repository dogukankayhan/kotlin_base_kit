package com.base.domain.usecase

import com.base.domain.repository.FavoritesRepository
import com.base.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : FlowUseCase<Unit, List<Movie>>() {
    override fun execute(parameters: Unit): Flow<List<Movie>> {
        return favoritesRepository.getFavorites()
    }
}
