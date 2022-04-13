package mxgenius.my.pacotedecarinho.data.remote

import mxgenius.my.pacotedecarinho.domain.model.ApiResponse
import mxgenius.my.pacotedecarinho.domain.responses.PopularClothingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ClothingApi {

    @GET("/pdc/clothing")
    suspend fun getAllClothing(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/pdc/clothing/popular")
    suspend fun getPopularClothing(
        @Query("page") page: Int = 1
    ): PopularClothingResponse

    @GET("/pdc/clothing/search")
    suspend fun searchClothing(
        @Query("name") name: String
    ): ApiResponse
}