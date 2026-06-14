package com.example.uesanapp.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.uesanapp.data.local.AppDatabase
import com.example.uesanapp.data.model.CountryModel
import com.example.uesanapp.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = FavoritesRepository(
        AppDatabase.getInstance(application).favoriteDao()
    )

    val favorites: StateFlow<List<CountryModel>> = repo
        .getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleFavorite(country: CountryModel) {
        viewModelScope.launch {
            val isAlreadyFavorite = favorites.value.any { it.name == country.name }
            repo.toggleFavorite(country, isAlreadyFavorite)
        }
    }
}