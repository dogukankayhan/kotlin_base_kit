package com.base.feature.favorites

import com.base.model.Product
import com.base.ui.base.UiEffect
import com.base.ui.base.UiEvent
import com.base.ui.base.UiState

sealed class FavoritesEvent : UiEvent {
    data class RemoveFavorite(val productId: String) : FavoritesEvent()
    object AddRandomFavorite : FavoritesEvent()
}

data class FavoritesState(
    val items: List<Product> = emptyList(),
    val isLoading: Boolean = true
) : UiState

sealed class FavoritesEffect : UiEffect {
    data class ShowToast(val message: String) : FavoritesEffect()
}
