package mxgenius.my.pacotedecarinho.presentation.components.banner

import mxgenius.my.pacotedecarinho.R


data class BannerItem(
    val id: Int,
    val title: String,
    val imageId: Int
)

object BannerContent{
    val bannerList = listOf(
        BannerItem(
            1,
            "Carnival",
            R.drawable.carnival
        ),
        BannerItem(
            2,
            "Festa Junina",
            R.drawable.festajunina
        ),
        BannerItem(
            3,
            "Summer",
            R.drawable.summer
        )
    )
}