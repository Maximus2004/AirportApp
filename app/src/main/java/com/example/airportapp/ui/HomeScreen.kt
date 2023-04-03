package com.example.airportapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.airportapp.R
import com.example.airportapp.data.AirportItem
import com.example.airportapp.data.FavouriteItem
import com.example.airportapp.ui.navigation.NavigationDestination
import com.example.airportapp.ui.theme.AirportAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel

object HomeScreen : NavigationDestination {
    override val route = "HomeScreen"
}

@Composable
fun HomeScreen(
    onClickItem: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val homeUiState: HomeUiState = viewModel.homeUiState.collectAsState().value
    val favouriteState: FavouriteState = viewModel.favouritesList.collectAsState().value
    val search: String = viewModel.search.collectAsState().value
    // TODO throw value to HomeUiState
    Scaffold(
        topBar = {
            AirportTopBar(title = stringResource(id = R.string.app_name))
        },
        modifier = modifier
    ) { paddingValues ->
        Box(modifier.padding(paddingValues)) {
            SearchResult(
                onStarDelete = { favourite -> viewModel.deleteFavourites(favourite) },
                favourites = favouriteState.favourites,
                modifier = Modifier
                    .padding(top = 110.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .fillMaxSize()
            )
            Card(
                elevation = 8.dp,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)
            ) {
                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    TextField(
                        value = search,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { search -> viewModel.setSearch(search) },
                        label = { Text(text = "Search") },
                        shape = MaterialTheme.shapes.small,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                    Divider(color = Color.Gray, thickness = 1.dp)
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    ) {
                        items(homeUiState.airports) { airport ->
                            AirportCard(airport, onClickItem = onClickItem)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AirportTopBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(title = { Text(title) }, modifier = modifier)
}

@Composable
fun AirportCard(airport: AirportItem, onClickItem: (String) -> Unit) {
    Row(modifier = Modifier.clickable { onClickItem(airport.iata_departure) }) {
        Text(text = airport.iata_departure, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = airport.name_departure)
    }
}