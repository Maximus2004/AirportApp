package com.example.airportapp

import android.app.Application
import com.example.airportapp.data.AppContainer
import com.example.airportapp.data.AppDataContainer

class AirportApplication : Application() {
    lateinit var container : AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}