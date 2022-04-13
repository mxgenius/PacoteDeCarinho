package mxgenius.my.pacotedecarinho.data.paging_source

import androidx.paging.PagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mxgenius.my.pacotedecarinho.data.remote.ClothingApi
import mxgenius.my.pacotedecarinho.data.remote.FakeClothingApi
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class SearchClothingSourceTest {

    private lateinit var clothingApi: ClothingApi
    private lateinit var clothing: List<Clothing>

    @Before
    fun setup(){
        clothingApi = FakeClothingApi()
        clothing = listOf(
            Clothing(
                id = 1,
                category = "accessories",
                name = "accessories1",
                image = "/images/clothing/accessories1.jpg",
                about = "all clothing",
                price =  6.36
            ),
            Clothing(
                id = 2,
                category = "accessories",
                name = "accessories2",
                image = "/images/clothing/accessories2.jpg",
                about = "all clothing",
                price = 6.54
            ),
            Clothing(
                id = 3,
                category = "accessories",
                name = "accessories3",
                image = "/images/clothing/accessories3.jpg",
                about = "all clothing",
                price = 8.25
            )
        )
    }

    @Test
    fun `Search api with existing clothing name, expect single clothing result, assert LoadResult_Page`() =
        runTest {
            val clothingSource = SearchClothingSource(clothingApi = clothingApi, query = "accessories1")
            assertEquals<PagingSource.LoadResult<Int, Clothing>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(clothing.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = clothingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }


    @Test
    fun `Search api with existing clothing name, expect multiple clothing result, assert LoadResult_Page`() =
        runTest {
            val clothingSource = SearchClothingSource(clothingApi = clothingApi, query = "acc")
            assertEquals<PagingSource.LoadResult<Int, Clothing>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(clothing.first(), clothing[2]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = clothingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with empty clothing name, assert empty clothing list and LoadResult_Page`() =
        runTest {
            val clothingSource = SearchClothingSource(clothingApi = clothingApi, query = "")
            val loadResult = clothingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = clothingApi.searchClothing("").clothing

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }

    @Test
    fun `Search api with non_existing clothing name, assert empty clothing list and LoadResult_Page`() =
        runTest {
            val clothingSource = SearchClothingSource(clothingApi = clothingApi, query = "Unknown")
            val loadResult = clothingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = clothingApi.searchClothing("Unknown").clothing

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }
}