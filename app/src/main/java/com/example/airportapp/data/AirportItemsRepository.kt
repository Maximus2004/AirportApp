package com.example.airportapp.data

import android.util.Log
import androidx.room.Query
import com.example.airportapp.ui.TAG
import kotlinx.coroutines.flow.Flow
import java.sql.Types.NULL

interface ItemsRepository {
    fun getFavouritesResult(departure: String): Flow<List<AirportItem>>
    fun getSearchResult(search: String): Flow<List<AirportItem>>
    fun getFavourites(): Flow<List<FavouriteItem>>
    fun showInfo(airport: AirportItem): Flow<List<FavouriteItem>>
    suspend fun insertFavourite(departure: String, destination: String)
    suspend fun deleteFavourite(departure: String, destination: String)
}

class AirportItemsRepository(val itemDAO: ItemDAO) : ItemsRepository{
    override fun getFavouritesResult(departure: String): Flow<List<AirportItem>> {
        return itemDAO.getFavouritesResult(departure)
    }

    override suspend fun insertFavourite(departure: String, destination: String) {
        itemDAO.insertFavourite(departure, destination)
    }

    override suspend fun deleteFavourite(departure: String, destination: String) {
        itemDAO.deleteFavourite(departure, destination)
    }

    override fun getSearchResult(search: String): Flow<List<AirportItem>> {
        Log.d(TAG, "Получаем данные $search");
        return itemDAO.getSearchResult(search)
    }

    override fun getFavourites(): Flow<List<FavouriteItem>> {
        return itemDAO.getFavourites()
    }

    override fun showInfo(airport: AirportItem): Flow<List<FavouriteItem>> {
        Log.d(TAG, "${airport.iata_destination} + ${airport.iata_departure}");
        return itemDAO.showInfo(airport.iata_departure, airport.iata_destination)
    }
}