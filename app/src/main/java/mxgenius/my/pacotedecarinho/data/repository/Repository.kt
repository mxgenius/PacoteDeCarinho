package mxgenius.my.pacotedecarinho.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.domain.repository.DataStoreOperations
import mxgenius.my.pacotedecarinho.domain.repository.LocalDataSource
import mxgenius.my.pacotedecarinho.domain.repository.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {
    fun getAllClothing(): Flow<PagingData<Clothing>> {
        return remote.getAllClothing()
    }

    fun getPopularClothing(): Flow<PagingData<Clothing>>{
        return remote.getPopularClothing()
    }

    fun searchClothing(query: String): Flow<PagingData<Clothing>> {
        return remote.searchClothing(query = query)
    }

    suspend fun getSelectedClothing(clothingId: Int): Clothing{
        return local.getSelectedClothing(clothingId = clothingId)
    }

    suspend fun getSelectedCategory(category: String): Clothing{
        return local.getSelectedCategory(category = category)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}