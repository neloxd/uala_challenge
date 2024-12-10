package com.jesusvilla.ualachallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

/**
 * Created by Jesus Villa on 9/12/24.
 */
@Composable
fun NavGraphSetUp(
    navController: NavHostController = rememberNavController(),
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    /*NavHost(navController = navController, startDestination = TODO()) {

    }*/
}