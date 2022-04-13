package mxgenius.my.pacotedecarinho.presentation.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mxgenius.my.pacotedecarinho.ui.theme.topAppBarBackgroundColor
import mxgenius.my.pacotedecarinho.ui.theme.topAppBarContentColor
import mxgenius.my.pacotedecarinho.R
import mxgenius.my.pacotedecarinho.ui.theme.DeepBlue

@Composable
fun HomeTopBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = DeepBlue,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon)
                )
            }
        }
    )
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar {}
}
