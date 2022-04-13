package mxgenius.my.pacotedecarinho.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import mxgenius.my.pacotedecarinho.R
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.navigation.Screen
import mxgenius.my.pacotedecarinho.ui.theme.AquaBlue
import mxgenius.my.pacotedecarinho.util.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ClothingItemRectangle(
    clothing: Clothing,
    navController: NavHostController
) {
    val painter = rememberImagePainter(data = "$BASE_URL${clothing.image}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(Screen.Details.passClothingId(clothingId = clothing.id)) },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
        border = BorderStroke(2.dp, AquaBlue)
    ){
        Box(
            Modifier
                .height(200.dp)
                .width(200.dp)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.clothing_image),
                contentScale = ContentScale.Crop
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Text(
                text = clothing.name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}