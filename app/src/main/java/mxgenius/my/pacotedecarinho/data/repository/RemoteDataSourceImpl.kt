package mxgenius.my.pacotedecarinho.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import mxgenius.my.pacotedecarinho.data.local.ClothingDatabase
import mxgenius.my.pacotedecarinho.data.paging_source.ClothingRemoteMediator
import mxgenius.my.pacotedecarinho.data.paging_source.PopularClothingRemoteMediator
import mxgenius.my.pacotedecarinho.data.paging_source.SearchClothingSource
import mxgenius.my.pacotedecarinho.data.remote.ClothingApi
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.domain.repository.RemoteDataSource
import mxgenius.my.pacotedecarinho.util.Constants.ITEMS_PER_PAGE

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val clothingApi: ClothingApi,
    private val clothingDatabase: ClothingDatabase
) : RemoteDataSource {

    private val clothingDao = clothingDatabase.clothingDao()


    override fun getAllClothing(): Flow<PagingData<Clothing>> {
        val pagingSourceFactory = { clothingDao.getAllClothing() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = ClothingRemoteMediator(
                clothingApi = clothingApi,
                clothingDatabase = clothingDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun getPopularClothing(): Flow<PagingData<Clothing>> {
        val pagingSourceFactory = { clothingDao.getPopularClothing() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = PopularClothingRemoteMediator(
                clothingApi = clothingApi,
                clothingDatabase = clothingDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchClothing(query: String): Flow<PagingData<Clothing>> {

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchClothingSource(clothingApi = clothingApi, query = query)
            }
        ).flow
    }
}