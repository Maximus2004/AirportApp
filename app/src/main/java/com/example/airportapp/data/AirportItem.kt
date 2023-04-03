package com.example.airportapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport_items")
data class AirportItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val iata_departure: String,
    val iata_destination: String,
    val name_departure: String,
    val name_destination: String,
    val passengers: Int
)
