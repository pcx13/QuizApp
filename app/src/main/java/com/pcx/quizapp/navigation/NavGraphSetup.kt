package com.pcx.quizapp.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pcx.quizapp.ui.capital.CapitalsScreen
import com.pcx.quizapp.ui.flag.FlagsScreen
import com.pcx.quizapp.ui.home.HomeScreen
import com.pcx.quizapp.ui.result.ResultScreen
import com.pcx.quizapp.util.Screen

@Composable
fun NavGraphSetup(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HOME_SCREEN
    ) {
        composable(route = Screen.HOME_SCREEN) {
            HomeScreen(
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(route = Screen.FLAGS_SCREEN) {
            FlagsScreen(
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(route = Screen.CAPITALS_SCREEN) {
            CapitalsScreen(
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(
            route = Screen.RESULT_SCREEN + "?trueCount={trueCount}",
            arguments = listOf(
                navArgument(name = "trueCount") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            BackHandler(true) {
                navController.navigate(Screen.HOME_SCREEN)
            }
            ResultScreen(
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
    }
}