package com.maltsev.stankinhack.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maltsev.stankinhack.screens.MainScreen
import com.maltsev.stankinhack.screens.Screen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(
            route = Screen.Main.route
        ) {
            MainScreen(navController = navController)
        }
    }
}