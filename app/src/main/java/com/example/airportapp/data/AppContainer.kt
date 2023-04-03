package com.example.airportapp.data

import android.content.Context

interface AppContainer {
    val itemsRepository: ItemsRepository
}

class AppDataContainer(context: Context) : AppContainer {
    override val itemsRepository: ItemsRepository = AirportItemsRepository(AirportDatabase.getDatabase(context).itemDao())
}