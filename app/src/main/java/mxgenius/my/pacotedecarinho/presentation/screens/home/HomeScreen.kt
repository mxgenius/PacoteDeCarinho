package mxgenius.my.pacotedecarinho.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import mxgenius.my.pacotedecarinho.R
import mxgenius.my.pacotedecarinho.navigation.Screen
import mxgenius.my.pacotedecarinho.presentation.common.ClothingItemCircled
import mxgenius.my.pacotedecarinho.presentation.common.ClothingItemRectangle
import mxgenius.my.pacotedecarinho.presentation.components.banner.BannerLayout
import mxgenius.my.pacotedecarinho.ui.theme.*


@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val clothing = homeViewModel.getAllClothing.collectAsLazyPagingItems()
    val popularClothing = homeViewModel.getPopularClothing.collectAsLazyPagingItems()
    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }
    Scaffold(
        backgroundColor = PacoteDeCarinhoTheme.colors.uiBackground,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .wrapContentHeight(),
                backgroundColor = AquaBlue,
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.pdc_logo),
                            contentDescription = "pdc logo",
                            modifier = Modifier
                                .clip(RoundedCornerShape(percent = 100))
                                .border(width = 3.dp, AquaBlue, shape = CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Pacote De Carinho",
                            color = DeepBlue,
                            style = MaterialTheme.typography.h1,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomMenu(
                items = listOf(
                    BottomMenuContent("Home", R.drawable.ic_home),
                    BottomMenuContent("Search", R.drawable.ic_search),
                    BottomMenuContent("Profile", R.drawable.ic_profile),

                )
            )
        }

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(13.dp)
        ) {
            item {
            Row() {
                Text(
                    text = "Promoções",
                    style = MaterialTheme.typography.h6,
                    color = PacoteDeCarinhoTheme.colors.brand,
                    modifier = Modifier.weight(1f)
                )
            }
        }
            item { BannerLayout()
                Divider(
                    color = DeepBlue,
                    thickness = 2.dp
                )

            }
            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                Row() {
                    Text(
                        text = "Tudo Ropas",
                        style = MaterialTheme.typography.h6,
                        color = PacoteDeCarinhoTheme.colors.brand,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .height(230.dp)
                ) {
                    LazyRow {
                        items(
                            items = clothing,
                            key = { clothing ->
                                clothing.id
                            }
                        ) { clothing ->
                            clothing?.let {
                                Box(modifier = Modifier.padding(8.dp))
                                {
                                    ClothingItemRectangle(
                                        clothing = it,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
                Divider(
                    color = DeepBlue,
                    thickness = 2.dp
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(width = 2.dp, color = AquaBlue, shape = CircleShape),
                        painter = painterResource(id = R.drawable.snow_white),
                        contentDescription = "SnowWhite"
                    )
                    Spacer(modifier = Modifier.width(13.dp))
                    Row() {
                        Text(
                            text = "favorito de Analisa",
                            style = MaterialTheme.typography.h6,
                            color = PacoteDeCarinhoTheme.colors.brand,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .height(150.dp)
                ) {
                    LazyRow {
                        items(
                            items = popularClothing,
                            key = { clothing ->
                                clothing.id
                            }
                        ) { clothing ->
                            clothing?.let {
                                Box(
                                    modifier = Modifier
                                        .padding(13.dp)
                                ) {
                                    ClothingItemCircled(
                                        clothing = it,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
                Divider(
                    color = DeepBlue,
                    thickness = 2.dp
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(width = 2.dp, color = AquaBlue, shape = CircleShape),
                        painter = painterResource(id = R.drawable.yuki),
                        contentDescription = "Yuki"
                    )
                    Spacer(modifier = Modifier.width(13.dp))
                    Row() {
                        Text(
                            text = "Lucis picks",
                            style = MaterialTheme.typography.h6,
                            color = PacoteDeCarinhoTheme.colors.brand,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .height(230.dp)
                ) {
                    LazyRow {
                        items(
                            items = clothing,
                            key = { clothing ->
                                clothing.id
                            }
                        ) { clothing ->
                            clothing?.let {
                                Box(modifier = Modifier.padding(8.dp))
                                {
                                    ClothingItemRectangle(
                                        clothing = it,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
                Divider(
                    color = DeepBlue,
                    thickness = 2.dp
                )
            }
            item { Spacer(modifier = Modifier.height(30.dp)) }

            item {
                Row() {
                    Text(
                        text = "Camilas Closet",
                        style = MaterialTheme.typography.h6,
                        color = PacoteDeCarinhoTheme.colors.brand,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .height(150.dp)
                ) {
                    LazyRow {
                        items(
                            items = popularClothing,
                            key = { clothing ->
                                clothing.id
                            }
                        ) { clothing ->
                            clothing?.let {
                                Box(
                                    modifier = Modifier
                                        .padding(13.dp)
                                ) {
                                    ClothingItemCircled(
                                        clothing = it,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
                Divider(
                    color = DeepBlue,
                    thickness = 2.dp
                )
            }
            item { Spacer(modifier = Modifier.height(70.dp)) }
        }
    }
}