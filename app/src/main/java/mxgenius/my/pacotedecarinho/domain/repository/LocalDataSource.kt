package mxgenius.my.pacotedecarinho.domain.repository

import mxgenius.my.pacotedecarinho.domain.model.Clothing

interface LocalDataSource {
    suspend fun getSelectedClothing(clothingId: Int): Clothing

    suspend fun getSelectedCategory(category: String): Clothing
}