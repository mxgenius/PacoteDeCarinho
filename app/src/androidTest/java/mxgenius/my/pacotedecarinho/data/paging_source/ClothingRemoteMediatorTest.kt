package mxgenius.my.pacotedecarinho.data.paging_source

import androidx.paging.*
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import mxgenius.my.pacotedecarinho.data.local.ClothingDatabase
import mxgenius.my.pacotedecarinho.data.remote.FakeClothingApi2
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import org.junit.After
import org.junit.Before
import org.junit.Test

class ClothingRemoteMediatorTest {

    private lateinit var clothingApi: FakeClothingApi2
    private lateinit var clothingDatabase: ClothingDatabase

    @Before
    fun setup() {
        clothingApi = FakeClothingApi2()
        clothingDatabase = ClothingDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun cleanup() {
        clothingDatabase.clearAllTables()
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runBlocking {
            val remoteMediator = ClothingRemoteMediator(
                clothingApi = clothingApi,
                clothingDatabase = clothingDatabase
            )
            val pagingState = PagingState<Int, Clothing>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            TestCase.assertTrue(result is RemoteMediator.MediatorResult.Success)
            TestCase.assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() =
        runBlocking {
            clothingApi.clearData(tableName = clothingApi.clothing)
            val remoteMediator = ClothingRemoteMediator(
                clothingApi = clothingApi,
                clothingDatabase = clothingDatabase
            )
            val pagingState = PagingState<Int, Clothing>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 100),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            TestCase.assertTrue(result is RemoteMediator.MediatorResult.Success)
            TestCase.assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)

        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() =
        runBlocking {
            clothingApi.addException()
            val remoteMediator = ClothingRemoteMediator(
                clothingApi = clothingApi,
                clothingDatabase = clothingDatabase
            )
            val pagingState = PagingState<Int, Clothing>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            TestCase.assertTrue(result is RemoteMediator.MediatorResult.Error)
        }
}