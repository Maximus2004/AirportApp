package com.example.airportapp.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airportapp.data.AirportItem
import com.example.airportapp.data.FavouriteItem
import com.example.airportapp.data.ItemsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search.asStateFlow()
    var homeUiState: StateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val favouritesList = itemsRepository.getFavourites().map{ FavouriteState(it) }.
        stateIn(
            scope = viewModelScope,
            SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = FavouriteState()
    )

    fun getDataSearchQuery() {
        homeUiState = itemsRepository.getSearchResult(_search.value).map{ HomeUiState(it) }.
            // походу stateIn может распараллелить один поток, чтобы другие "подписчики" тоже получили к нему доступ
            // вместе с viewModelScope завершится и передача этого потока в UI
            stateIn(
                scope = viewModelScope,
                SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
    }

    fun deleteFavourites(favourite: FavouriteItem) {
        viewModelScope.launch {
            itemsRepository.deleteFavourite(favourite.departure, favourite.destination)
        }
    }

    fun setSearch(searchQuery: String) {
        Log.d(TAG, "Обновляем поиск $searchQuery");
        _search.update { searchQuery }
        getDataSearchQuery()
        Log.d(TAG, "Обновляем поиск ${_search.value}");
    }

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(val airports: List<AirportItem> = listOf())
data class FavouriteState(val favourites: List<FavouriteItem> = listOf())