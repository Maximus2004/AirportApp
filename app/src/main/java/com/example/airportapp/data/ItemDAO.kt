package com.example.airportapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.airportapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.flow.Flow

// data access object
@Dao
interface ItemDAO {
    // показывает ВСЕ рейсы, которые уходят от этого аэропорта
    @Query("SELECT * from airport_items WHERE iata_departure = :departure")
    fun getFavouritesResult(departure: String): Flow<List<AirportItem>>

    @Query ("SELECT * FROM favourite_items")
    fun getFavourites(): Flow<List<FavouriteItem>>

    // показывает все аэропорты по данному поисковому запросу
    @Query("SELECT * from airport_items WHERE (iata_departure LIKE :search) OR (name_departure LIKE :search)")
    fun getSearchResult(search: String): Flow<List<AirportItem>>

    // берёт рейс из результатов поиска и проверяет, находится ли он в избранном
    @Query("SELECT * FROM favourite_items WHERE (departure = :departure) AND (destination = :destination)")
    fun showInfo(departure: String, destination: String): Flow<List<FavouriteItem>>

    @Query("INSERT INTO favourite_items VALUES(NULL, :departure, :destination)")
    suspend fun insertFavourite(departure: String, destination: String)

    @Query("DELETE FROM favourite_items WHERE (:departure = departure) AND (:destination = destination)")
    suspend fun deleteFavourite(departure: String, destination: String)
}