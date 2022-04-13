package mxgenius.my.pacotedecarinho.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mxgenius.my.pacotedecarinho.ui.theme.AquaBlue
import mxgenius.my.pacotedecarinho.ui.theme.ghost_white

@Composable
fun TopAppBarWithBack(
    onBackClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colors.background
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .background(backgroundColor)
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "On Back",
                tint = ghost_white,
                modifier = Modifier.size(32.dp, 32.dp)

            )
        }
    }
}