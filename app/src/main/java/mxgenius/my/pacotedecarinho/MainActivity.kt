package mxgenius.my.pacotedecarinho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import mxgenius.my.pacotedecarinho.navigation.SetupNavGraph
import mxgenius.my.pacotedecarinho.ui.theme.PacoteDeCarinhoTheme


@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PacoteDeCarinhoTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}