package mxgenius.my.pacotedecarinho.presentation.screens.image

import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import mxgenius.my.pacotedecarinho.R
import mxgenius.my.pacotedecarinho.presentation.screens.details.DetailsViewModel
import mxgenius.my.pacotedecarinho.ui.theme.INFO_ICON_SIZE
import mxgenius.my.pacotedecarinho.ui.theme.SMALL_PADDING
import mxgenius.my.pacotedecarinho.util.Constants


@ExperimentalCoilApi
@Composable
fun ImageScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
){
    val selectedClothing by detailsViewModel.selectedClothing.collectAsState()

    selectedClothing?.let{ clothing ->
        ShowClothingImage(
            clothingImage = clothing.image,
            onCloseClicked = {
                navController.popBackStack()
            }
        )
    }
}

@ExperimentalCoilApi
@Composable
fun ShowClothingImage(
    clothingImage: String,
    onCloseClicked: () -> Unit

) {
    val imageUrl = "${Constants.BASE_URL}${clothingImage}"
    val painter = rememberImagePainter(imageUrl) {
        error(R.drawable.ic_placeholder)
    }

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            painter = painter,
            contentDescription = stringResource(id = R.string.clothing_image),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    tint = Color.Black
                )
            }
        }
    }
}