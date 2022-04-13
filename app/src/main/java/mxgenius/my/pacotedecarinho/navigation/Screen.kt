package mxgenius.my.pacotedecarinho.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{clothingId}") {
        fun passClothingId(clothingId: Int): String {
            return "details_screen/$clothingId"
        }
    }
    object Search : Screen("search_screen")
    object Image : Screen("image_screen/{clothingId}") {
        fun passClothingId(clothingId: Int): String {
            return "image_screen/$clothingId"
        }
    }
    object Checkout: Screen("checkout_screen")
}
