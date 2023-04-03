package com.example.airportapp.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.airportapp.data.AirportItem
import com.example.airportapp.data.FavouriteItem
import kotlinx.coroutines.flow.Flow

var TAG = "MYTAG"

@Composable
fun SearchResult(
    onStarDelete: (FavouriteItem) -> Unit,
    favourites: List<FavouriteItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        items(favourites) { favourite ->
            SearchResultCard(favourite, onStarDelete)
        }
    }
}

@Composable
fun SearchResultCard(
    favourite: FavouriteItem,
    onDeleteStar: (FavouriteItem) -> Unit
) {
    Card(elevation = 4.dp) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column() {
                Text(text = "DEPART", style = MaterialTheme.typography.body2)
                Row() {
                    Text(
                        text = favourite.departure,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    // TODO добавить авто вывод расшифровки
                    Text(text = "Sheremetyevo International Airport")
                }
                Text(text = "ARRIVE", style = MaterialTheme.typography.body2)
                Row() {
                    Text(
                        text = favourite.destination,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    // TODO добавить авто вывод расшифровки
                    Text(text = "Vienna International Airport")
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier
                    .size(45.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { onDeleteStar(favourite) }
            )
        }
    }
}

@Composable
fun FavouriteList(
    onFavouriteShow: (AirportItem) -> Boolean,
    onCreateStar: (departure: String, destination: String) -> Unit,
    onDeleteStar: (departure: String, destination: String) -> Unit,
    airports: List<AirportItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        items(airports) { airport ->
            FavouriteCard(airport = airport, onCreateStar = onCreateStar, onDeleteStar = onDeleteStar, onFavouriteShow = onFavouriteShow)
        }
    }
}

@Composable
fun FavouriteCard(
    onFavouriteShow: (AirportItem) -> Boolean,
    airport: AirportItem,
    onCreateStar: (departure: String, destination: String) -> Unit,
    onDeleteStar: (departure: String, destination: String) -> Unit
) {
    // запрос делается единожды в начале выполнения программы, далее мы сами корректтируем поведение звезды
    var starState by remember { mutableStateOf(onFavouriteShow(airport)) }
    Log.d(TAG, starState.toString())
    Card(elevation = 4.dp) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column() {
                Text(text = "DEPART", style = MaterialTheme.typography.body2)
                Row() {
                    Text(
                        text = airport.iata_departure,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Text(text = airport.name_departure)
                }
                Text(text = "ARRIVE", style = MaterialTheme.typography.body2)
                Row() {
                    Text(
                        text = airport.iata_destination,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Text(text = airport.name_destination)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (starState) {
                    Color.Yellow
                } else {
                    Color.Gray
                },
                modifier = Modifier
                    .size(45.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        if (starState) {
                            onDeleteStar(airport.iata_departure, airport.iata_destination)
                            starState = false
                            Log.d(TAG, "Clicked at second")
                        } else {
                            onCreateStar(airport.iata_departure, airport.iata_destination)
                            starState = true
                            Log.d(TAG, "Clicked at first")
                        }
                    }
            )
        }
    }
}