package com.example.airportapp.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.airportapp.data.AirportItem
import com.example.airportapp.data.FavouriteItem
import com.example.airportapp.ui.DetailScreen
import com.example.airportapp.ui.HomeScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController, startDestination = HomeScreen.route, modifier = modifier
    ) {
        composable(route = HomeScreen.route) {
            HomeScreen(onClickItem = { navController.navigate("${DetailScreen.route}/${it}") })
        }
        composable(route = DetailScreen.routeWithArgs,
            arguments = listOf(navArgument(name = DetailScreen.depart) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                backStackEntry.arguments?.getString(DetailScreen.depart)
                    ?: error("IATA cannot be null"),
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}