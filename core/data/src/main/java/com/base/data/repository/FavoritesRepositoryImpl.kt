package com.base.data.repository

import com.base.domain.repository.FavoritesRepository
import com.base.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepositoryImpl @Inject constructor() : FavoritesRepository {

    private val _favorites = MutableStateFlow<List<Product>>(emptyList())
    
    companion object {
        private const val PRODUCT_1_PRICE = 10.0
        private const val PRODUCT_2_PRICE = 20.0
    }
    
    // Initial dummy data
    init {
        _favorites.value = listOf(
            Product("1", "Product 1", PRODUCT_1_PRICE, null, "Description 1"),
            Product("2", "Product 2", PRODUCT_2_PRICE, null, "Description 2")
        )
    }

    override fun getFavorites(): Flow<List<Product>> = _favorites.asStateFlow()

    override suspend fun addFavorite(product: Product) {
        val current = _favorites.value.toMutableList()
        if (current.none { it.id == product.id }) {
            current.add(product)
            _favorites.value = current
        }
    }

    override suspend fun removeFavorite(productId: String) {
        val current = _favorites.value.toMutableList()
        current.removeAll { it.id == productId }
        _favorites.value = current
    }

    override suspend fun isFavorite(productId: String): Boolean {
        return _favorites.value.any { it.id == productId }
    }
}
