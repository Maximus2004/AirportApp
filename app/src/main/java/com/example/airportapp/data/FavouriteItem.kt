package com.example.airportapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_items")
data class FavouriteItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val departure: String,
    val destination: String
)
