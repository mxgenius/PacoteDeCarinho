package mxgenius.my.pacotedecarinho.domain.use_cases.get_all_clothing

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import mxgenius.my.pacotedecarinho.data.repository.Repository
import mxgenius.my.pacotedecarinho.domain.model.Clothing

class GetAllClothingUseCase(
    private val repository: Repository

) {
    operator fun invoke(): Flow<PagingData<Clothing>> {
        return repository.getAllClothing()
    }
}