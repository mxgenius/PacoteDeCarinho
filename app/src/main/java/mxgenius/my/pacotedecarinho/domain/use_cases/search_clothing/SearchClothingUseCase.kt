package mxgenius.my.pacotedecarinho.domain.use_cases.search_clothing

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import mxgenius.my.pacotedecarinho.data.repository.Repository
import mxgenius.my.pacotedecarinho.domain.model.Clothing

class SearchClothingUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Clothing>> {
        return repository.searchClothing(query = query)
    }
}