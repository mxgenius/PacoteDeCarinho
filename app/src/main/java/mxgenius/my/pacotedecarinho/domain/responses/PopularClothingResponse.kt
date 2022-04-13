package mxgenius.my.pacotedecarinho.domain.responses

import mxgenius.my.pacotedecarinho.domain.model.Clothing

data class PopularClothingResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val popularClothing: List<Clothing> = emptyList(),
    val lastUpdated: Long? = null
)