package com.base.domain.repository

import com.base.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<List<Movie>>
    suspend fun addFavorite(movie: Movie)
    suspend fun removeFavorite(movieId: Int)
    suspend fun isFavorite(movieId: Int): Boolean
}
