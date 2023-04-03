package com.example.airportapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.airportapp.data.FavouriteItem
import com.example.airportapp.ui.navigation.NavigationDestination
import com.example.airportapp.ui.theme.AirportAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel

object DetailScreen : NavigationDestination {
    override val route = "DetailScreen"
    const val depart = "departure"
    val routeWithArgs: String = "$route/{$depart}"
}

@Composable
fun DetailScreen(
    title: String,
    onNavigateBack: () -> Unit,
    viewModel: DetailScreenViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val detailUiState: DetailUiState = viewModel.detailUiState.collectAsState().value
    Scaffold(topBar = {
        AirportTopBar(
            title = "From $title",
            onNavigateBack = onNavigateBack
        )
    }) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            FavouriteList(
                onDeleteStar = { departure, destination -> viewModel.deleteFavourites(departure, destination) },
                onCreateStar = { departure, destination -> viewModel.insertFavourites(departure, destination) },
                airports = detailUiState.airports,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                onFavouriteShow = { favourite -> viewModel.showInfo(favourite) }
            )
        }
    }
}

@Composable
fun AirportTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        title = { Text(title) },
        modifier = modifier
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun DetailScreenPreview() {
    AirportAppTheme {
        DetailScreen(
            "TITLE",
            {}
        )
    }
}