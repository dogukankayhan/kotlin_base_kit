package com.base.feature.favorites

import androidx.lifecycle.viewModelScope
import com.base.domain.repository.FavoritesRepository
import com.base.model.Product
import com.base.ui.base.LifecycleViewModel
import com.base.ui.base.UiEffect
import com.base.ui.base.UiEvent
import com.base.ui.base.UiState
import com.base.feature.favorites.coordinator.FavoritesCoordinator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val coordinator: com.base.feature.favorites.coordinator.FavoritesCoordinator
) : LifecycleViewModel<FavoritesEvent, FavoritesState, FavoritesEffect>() {

    override fun createInitialState(): FavoritesState = FavoritesState()

    init {
        viewModelScope.launch {
            favoritesRepository.getFavorites().collectLatest { items ->
                setState { copy(items = items, isLoading = false) }
            }
        }
    }
    
    override fun onResume() {
        super.onResume()
        // Example of lifecycle hook
        setEffect(FavoritesEffect.ShowToast("Favorites Resumed"))
    }

    override fun handleEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.RemoveFavorite -> removeFavorite(event.productId)
            is FavoritesEvent.AddRandomFavorite -> addRandomFavorite()
        }
    }

    private fun removeFavorite(productId: String) {
        viewModelScope.launch {
            favoritesRepository.removeFavorite(productId)
        }
    }
    
    private fun addRandomFavorite() {
        // ... (Logic remains same)
        val id = System.currentTimeMillis().toString()
        val product = Product(
            id = id,
            name = "Product $id",
            price = (10..100).random().toDouble(),
            description = "Randomly added product"
        )
        viewModelScope.launch {
            favoritesRepository.addFavorite(product)
        }
    }
}
