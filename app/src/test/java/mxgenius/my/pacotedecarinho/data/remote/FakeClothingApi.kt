package mxgenius.my.pacotedecarinho.data.remote

import mxgenius.my.pacotedecarinho.domain.model.ApiResponse
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.domain.responses.PopularClothingResponse

class FakeClothingApi: ClothingApi {

    private val clothing = listOf(
        Clothing(
            id = 1,
            category = "accessories",
            name = "accessories1",
            image = "/images/clothing/accessories1.jpg",
            about = "all clothing",
            price = 6.36
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

    override suspend fun getAllClothing(page: Int): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    override suspend fun getPopularClothing(page: Int): PopularClothingResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchClothing(name: String): ApiResponse {
        val searchedClothing = findClothing(name = name)
        return ApiResponse(
            success = true,
            message = "ok",
            clothing = searchedClothing
        )
    }

    private fun findClothing(name: String): List<Clothing> {
        val founded = mutableListOf<Clothing>()
        return if (name.isNotEmpty()) {
            clothing.forEach { clothing ->
                if (clothing.name.lowercase().contains(name.lowercase())) {
                    founded.add(clothing)
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}