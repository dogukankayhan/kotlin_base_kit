package com.base.domain.repository

import com.base.model.Product
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<List<Product>>
    suspend fun addFavorite(product: Product)
    suspend fun removeFavorite(productId: String)
    suspend fun isFavorite(productId: String): Boolean
}
