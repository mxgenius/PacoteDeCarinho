package mxgenius.my.pacotedecarinho.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import mxgenius.my.pacotedecarinho.data.local.ClothingDatabase
import mxgenius.my.pacotedecarinho.data.remote.ClothingApi
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.domain.model.ClothingRemoteKeys
import javax.inject.Inject

@ExperimentalPagingApi
class PopularClothingRemoteMediator @Inject constructor(
    private val clothingApi: ClothingApi,
    clothingDatabase: ClothingDatabase
) : RemoteMediator<Int, Clothing>(){

    private val clothingDao = clothingDatabase.clothingDao()
    private val clothingRemoteKeysDao = clothingDatabase.clothingRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = clothingRemoteKeysDao.getRemoteKeys(clothingId = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {

            InitializeAction.SKIP_INITIAL_REFRESH
        } else {

            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Clothing>): MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = clothingApi.getPopularClothing(page = page)
            if (response.popularClothing.isNotEmpty()) {
                    if (loadType == LoadType.REFRESH) {
                        clothingDao.deleteAllClothing()
                        clothingRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.popularClothing.map { clothing ->
                        ClothingRemoteKeys(
                            id = clothing.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    clothingRemoteKeysDao.addAllRemoteKeys(clothingRemoteKeys = keys)
                    clothingDao.addClothing(clothing = response.popularClothing)
                }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Clothing>
    ): ClothingRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                clothingRemoteKeysDao.getRemoteKeys(clothingId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Clothing>
    ): ClothingRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { clothing ->
                clothingRemoteKeysDao.getRemoteKeys(clothingId = clothing.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Clothing>
    ): ClothingRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { clothing ->
                clothingRemoteKeysDao.getRemoteKeys(clothingId = clothing.id)
            }
    }

}