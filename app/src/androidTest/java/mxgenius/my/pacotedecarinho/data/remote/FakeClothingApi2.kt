package mxgenius.my.pacotedecarinho.data.remote

import mxgenius.my.pacotedecarinho.domain.model.ApiResponse
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.domain.responses.PopularClothingResponse
import java.io.IOException

class FakeClothingApi2 : ClothingApi {

    var clothing = listOf(
        Clothing(
            id = 1,
            category = "accessories",
            name = "accessories1",
            image = "/images/clothing/accessories1.jpg",
            about = "all clothing",
            price = getRandomPrice()
        ),
        Clothing(
            id = 2,
            category = "accessories",
            name = "accessories2",
            image = "/images/clothing/accessories2.jpg",
            about = "all clothing",
            price = getRandomPrice()
        ),
        Clothing(
            id = 3,
            category = "accessories",
            name = "accessories3",
            image = "/images/clothing/accessories3.jpg",
            about = "all clothing",
            price = getRandomPrice()
        )
    )

    fun clearData(tableName: List<Clothing>): List<Clothing> {
        return emptyList()

    }

    private var exception = false

    fun addException() {
        exception = true
    }

    override suspend fun getAllClothing(page: Int): ApiResponse {

        if (exception) {
            throw IOException()
        }
        require(page <= clothing.size)
        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = calculatePage(
                clothing = clothing,
                page = page,
                limit = 3
            )["prevPage"],
            nextPage = calculatePage(
                clothing = clothing,
                page = page,
                limit = 3
            )["nextPage"],
            clothing = provideClothing(
                clothing = clothing,
                page = page,
                limit = 3
            ),
            lastUpdated = System.currentTimeMillis()
        )
    }

    override suspend fun getPopularClothing(page: Int): PopularClothingResponse {
        TODO("Not yet implemented")
    }

    fun getRandomPrice(): Double = (1..150).random() + 0.99

    override suspend fun searchClothing(name: String): ApiResponse {
        return ApiResponse(
            success = false,
            message = "ok",
            clothing = findClothing(query = name)
        )
    }

    private fun calculatePage(
        clothing: List<Clothing>,
        page: Int,
        limit: Int
    ): Map<String, Int?> {
        val allClothing = clothing.windowed(
            size = limit,
            step = limit,
            partialWindows = true
        )
        require(page <= allClothing.size)
        val prevPage = if (page == 1) null else page - 1
        val nextPage = if (page == allClothing.size) null else page + 1
        return mapOf(
            "prevPage" to prevPage,
            "nextPage" to nextPage
        )
    }

        private fun provideClothing(
            clothing: List<Clothing>,
            page: Int,
            limit: Int
        ): List<Clothing> {
            val allClothing = clothing.windowed(
                size = limit,
                step = limit,
                partialWindows = true
            )
            require(page > 0 && page <= allClothing.size)
            return allClothing[page - 1]
        }

        private fun findClothing(query: String?): List<Clothing> {
            val founded = mutableListOf<Clothing>()
            return if (!query.isNullOrEmpty()) {
                clothing.forEach { clothing ->
                    if (clothing.name.lowercase().contains(query.lowercase())) {
                        founded.add(clothing)
                    }
                }
                founded
            } else {
                emptyList()
        }
    }
}