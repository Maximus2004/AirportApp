package com.example.airportapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airportapp.data.AirportItem
import com.example.airportapp.data.FavouriteItem
import com.example.airportapp.data.ItemsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {
    private val departure: String = checkNotNull(savedStateHandle[DetailScreen.depart])
    val detailUiState = itemsRepository.getFavouritesResult(departure).map { DetailUiState(it) }
        .stateIn(
            scope = viewModelScope,
            SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = DetailUiState()
        )

    fun showInfo(airport: AirportItem): Boolean {
        return itemsRepository.showInfo(airport = airport).map { ForCheckUiState(it) }
            .stateIn(
                scope = viewModelScope,
                SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ForCheckUiState()
            ).value.favourites.isNotEmpty()
    }

    fun insertFavourites(departure: String, destination: String) {
        viewModelScope.launch {
            itemsRepository.insertFavourite(departure, destination)
        }
    }

    fun deleteFavourites(departure: String, destination: String) {
        viewModelScope.launch {
            itemsRepository.deleteFavourite(departure, destination)
        }
    }

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}

data class DetailUiState(val airports: List<AirportItem> = listOf())
data class ForCheckUiState(val favourites: List<FavouriteItem>  = listOf())