package mxgenius.my.pacotedecarinho.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import mxgenius.my.pacotedecarinho.ui.theme.DeepBlue
import mxgenius.my.pacotedecarinho.util.Constants


@ExperimentalCoilApi
@Composable
fun ClothingItemCircled(
    clothing: Clothing,
    navController: NavHostController
) {
    val painter = rememberImagePainter(data = "${Constants.BASE_URL}${clothing.image}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(Screen.Details.passClothingId(clothingId = clothing.id)) },
        shape = CircleShape,
        elevation = 5.dp,
        border = BorderStroke(2.dp, AquaBlue)
    ){
        /**
         * Maybe add a handle for paging result
         */
        Box(
            Modifier
                .height(100.dp)
                .width(100.dp)
        ){
            Image(
                painter = painter,
                contentDescription = stringResource(R.string.clothing_image),
                contentScale = ContentScale.Crop
            )
        }
    }
}