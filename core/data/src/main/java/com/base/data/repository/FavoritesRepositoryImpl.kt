package com.base.data.repository

import com.base.domain.repository.FavoritesRepository
import com.base.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepositoryImpl @Inject constructor() : FavoritesRepository {

    private val _favorites = MutableStateFlow<List<Movie>>(emptyList())

    override fun getFavorites(): Flow<List<Movie>> = _favorites.asStateFlow()

    override suspend fun addFavorite(movie: Movie) {
        _favorites.update { current ->
            if (!current.any { it.id == movie.id }) {
                current + movie
            } else {
                current
            }
        }
    }

    override suspend fun removeFavorite(movieId: Int) {
        _favorites.update { current ->
            current.filterNot { it.id == movieId }
        }
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return _favorites.value.any { it.id == movieId }
    }
}
