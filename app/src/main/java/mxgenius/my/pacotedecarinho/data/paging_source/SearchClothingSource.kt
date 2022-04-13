package mxgenius.my.pacotedecarinho.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import mxgenius.my.pacotedecarinho.data.remote.ClothingApi
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import javax.inject.Inject

class SearchClothingSource (
    private val clothingApi: ClothingApi,
    private val query: String
) : PagingSource<Int, Clothing>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Clothing> {
        return try {
            val apiResponse = clothingApi.searchClothing(name = query)
            val clothing = apiResponse.clothing
            if (clothing.isNotEmpty()) {
                LoadResult.Page(
                    data = clothing,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Clothing>): Int? {
        return state.anchorPosition
    }
}