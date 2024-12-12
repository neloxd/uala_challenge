package com.jesusvilla.ualachallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.jesusvilla.ualachallenge.ui.model.CityModel
import com.jesusvilla.ualachallenge.ui.screen.FilterListScreen
import com.jesusvilla.ualachallenge.ui.screen.MapScreen
import com.jesusvilla.ualachallenge.ui.viewModel.MainViewModel

/**
 * Created by Jesus Villa on 9/12/24.
 */
@Composable
fun NavGraphSetUp(
    navController: NavHostController = rememberNavController(),
) {
    val viewModel: MainViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
        composable(Screen.ListScreen.route) {
            FilterListScreen(
                viewModel =  viewModel,
                onSelected = {
                    navController.navigate(
                        //route = ROUTER_MAP
                        route = Screen.MapScreen.withArgs(Gson().toJson(it))
                    )
                }
            )
        }

        composable(Screen.MapScreen.route + "/{city}",
            arguments = listOf(
                navArgument("city") {
                    type = NavType.StringType
                    nullable = false
                },
            )) { navBackStackEntry ->
            val city: CityModel = Gson().fromJson(navBackStackEntry.arguments?.getString("city")!!, CityModel::class.java)
            MapScreen(
                cityModel = city,
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object ListScreen: Screen("list_screen")
    data object MapScreen: Screen("map_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}