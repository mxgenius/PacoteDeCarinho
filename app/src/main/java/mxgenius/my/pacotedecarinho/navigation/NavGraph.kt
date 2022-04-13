package mxgenius.my.pacotedecarinho.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import mxgenius.my.pacotedecarinho.presentation.screens.checkout.CheckoutScreen
import mxgenius.my.pacotedecarinho.presentation.screens.details.DetailsScreen
import mxgenius.my.pacotedecarinho.presentation.screens.home.HomeScreen
import mxgenius.my.pacotedecarinho.presentation.screens.image.ImageScreen
import mxgenius.my.pacotedecarinho.presentation.screens.search.SearchScreen
import mxgenius.my.pacotedecarinho.presentation.screens.splash.SplashScreen
import mxgenius.my.pacotedecarinho.presentation.screens.welcome.WelcomeScreen
import mxgenius.my.pacotedecarinho.util.Constants.DETAILS_ARGUMENT_KEY

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = Screen.Image.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
        })
        ){
            ImageScreen(navController = navController)
        }
        composable(route = Screen.Checkout.route){
            CheckoutScreen(navController = navController)
        }
    }
}
