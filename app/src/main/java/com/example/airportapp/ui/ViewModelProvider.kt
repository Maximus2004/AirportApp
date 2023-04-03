package com.example.airportapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.airportapp.AirportApplication

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeScreenViewModel(airportApplication().container.itemsRepository)
        }
        initializer {
            DetailScreenViewModel(this.createSavedStateHandle(), airportApplication().container.itemsRepository)
        }
    }
}

// как бы создаём объект класса AirportApplication, но каким-то специфичным методом
fun CreationExtras.airportApplication(): AirportApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AirportApplication)