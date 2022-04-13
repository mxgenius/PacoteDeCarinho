package mxgenius.my.pacotedecarinho.presentation.screens.details

import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import mxgenius.my.pacotedecarinho.R
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.navigation.Screen
import mxgenius.my.pacotedecarinho.presentation.components.TopAppBarWithBack
import mxgenius.my.pacotedecarinho.ui.theme.*
import mxgenius.my.pacotedecarinho.util.Constants.BASE_URL

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedClothing: Clothing?,
    colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    val imageUrl = "${BASE_URL}${selectedClothing?.image}"
    val painter = rememberImagePainter(imageUrl) {
        error(R.drawable.ic_placeholder)
    }

    LaunchedEffect(key1 = selectedClothing) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(parseColor(darkVibrant))
        )
    }
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                onBackClick = {
                    navController.popBackStack()
                },
                backgroundColor = Color(parseColor(darkVibrant))
            )
        }, backgroundColor = Color(parseColor(darkVibrant)),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .background(Color(parseColor(darkVibrant)))
            )
            {
                selectedClothing?.let { clothing ->
                    Card(
                        modifier = Modifier
                            .height(280.dp)
                            .fillMaxWidth(),
                        elevation = 5.dp,
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            navController.navigate(
                                Screen.Image.passClothingId(
                                    clothingId = clothing.id
                                )
                            )
                        }
                    )
                    {
                        Image(
                            painter = painter,
                            contentDescription = "clothing Image",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(22.dp))
                }
                Column() {
                    selectedClothing?.let { clothing ->
                        Text(
                            text = clothing.name,
                            style = MaterialTheme.typography.h6,
                            color = ghost_white
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = clothing.about,
                            style = MaterialTheme.typography.caption,
                            color = ghost_white
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_reals),
                                contentDescription = "Reals",
                                modifier = Modifier.size(24.dp),
                                tint = ghost_white
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = clothing.price.toString(),
                                style = MaterialTheme.typography.h3,
                                color = ghost_white,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.padding(20.dp))
                        Divider(color = ghost_white, thickness = 1.dp)
                        Spacer(modifier = Modifier.padding(20.dp))
                        ClothingAddToCartButton(navController = navController, color = darkVibrant)
                    }
                }
            }
        }
    )
}

@Composable
fun ClothingAddToCartButton(navController: NavController, color: String) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = {
                Toast.makeText(context, "Clothing Added to Cart", Toast.LENGTH_LONG).show()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = ghost_white),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Add to Cart",
                color = Color(parseColor(color)),
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color(parseColor(color)),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(20.dp, 20.dp)
            )
        }
    }
}