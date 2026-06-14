package com.example.uesanapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class CountryModel(
    @PrimaryKey val name: String,
    val ranking: Int,
    val imageUrl: String,
    val isFavorite: Boolean = false
)