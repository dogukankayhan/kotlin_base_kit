package com.base.feature.favorites.coordinator

import com.base.navigation.Navigator
import javax.inject.Inject

interface FavoritesCoordinator {
    suspend fun goBack()
}

class DefaultFavoritesCoordinator @Inject constructor(
    private val navigator: Navigator
) : FavoritesCoordinator {

    override suspend fun goBack() {
        navigator.popBackStack()
    }
}
