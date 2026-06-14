package com.example.uesanapp.data.repository

import com.example.uesanapp.data.local.FavoriteDao
import com.example.uesanapp.data.model.CountryModel
import kotlinx.coroutines.flow.Flow

class FavoritesRepository(private val dao: FavoriteDao) {

    fun getAllFavorites(): Flow<List<CountryModel>> = dao.getAllFavorites()

    suspend fun toggleFavorite(country: CountryModel, isCurrentlyFavorite: Boolean) {
        if (isCurrentlyFavorite) {
            dao.removeFavorite(country)
        } else {
            dao.addFavorite(country.copy(isFavorite = true))
        }
    }
}