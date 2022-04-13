package mxgenius.my.pacotedecarinho.data.repository

import mxgenius.my.pacotedecarinho.data.local.ClothingDatabase
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.domain.repository.LocalDataSource

class LocalDataSourceImpl(clothingDatabase: ClothingDatabase) : LocalDataSource {

    private val clothingDao = clothingDatabase.clothingDao()

    override suspend fun getSelectedClothing(clothingId: Int) : Clothing {
        return clothingDao.getSelectedClothing(clothingId = clothingId)
    }

    override suspend fun getSelectedCategory(category: String): Clothing {
        return clothingDao.getSelectedCategory(category = category)
    }
}