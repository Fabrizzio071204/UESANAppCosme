package com.example.uesanapp.data.local

import androidx.room.*
import com.example.uesanapp.data.model.CountryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(country: CountryModel)

    @Delete
    suspend fun removeFavorite(country: CountryModel)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<CountryModel>>
}