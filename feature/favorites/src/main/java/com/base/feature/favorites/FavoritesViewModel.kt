package com.base.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.domain.usecase.AddFavoriteUseCase
import com.base.domain.usecase.GetFavoritesUseCase
import com.base.domain.usecase.RemoveFavoriteUseCase
import com.base.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Pokemon>>(emptyList())
    val favorites: StateFlow<List<Pokemon>> = _favorites.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        _isLoading.value = true
        getFavoritesUseCase()
            .onEach {
                _favorites.value = it
                _isLoading.value = false
            }
            .launchIn(viewModelScope)
    }

    fun toggleFavorite(pokemon: Pokemon) {
        viewModelScope.launch {
            if (pokemon.isFavorite) {
                removeFavoriteUseCase(pokemon.id)
            } else {
                addFavoriteUseCase(pokemon)
            }
        }
    }
}
