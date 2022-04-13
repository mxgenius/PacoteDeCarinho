package mxgenius.my.pacotedecarinho.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import mxgenius.my.pacotedecarinho.domain.model.Clothing

interface RemoteDataSource {

    fun getAllClothing(): Flow<PagingData<Clothing>>
    fun getPopularClothing(): Flow<PagingData<Clothing>>
    fun searchClothing(query: String): Flow<PagingData<Clothing>>
}