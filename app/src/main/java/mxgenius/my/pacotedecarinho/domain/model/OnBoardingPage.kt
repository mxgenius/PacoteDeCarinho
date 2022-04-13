package mxgenius.my.pacotedecarinho.domain.model

import androidx.annotation.DrawableRes
import mxgenius.my.pacotedecarinho.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
){
    object First: OnBoardingPage(
        image = R.drawable.greetings,
        title = "Bem Vindo",
        description = "Tudo bem?, esperamos que goste da nossa loja."
    )
    object Second : OnBoardingPage(
        image = R.drawable.baby,
        title = "Paixão",
        description = "Tudo o que temos ele é do nosso coração para o seu."
    )

    object Third : OnBoardingPage(
        image = R.drawable.baby_clothes,
        title = "Roupas",
        description = "Muitas roupas para seu bebê."
    )
}