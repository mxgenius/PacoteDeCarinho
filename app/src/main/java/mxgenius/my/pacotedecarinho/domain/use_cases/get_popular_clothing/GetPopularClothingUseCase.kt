package mxgenius.my.pacotedecarinho.domain.use_cases.get_popular_clothing

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import mxgenius.my.pacotedecarinho.data.repository.Repository
import mxgenius.my.pacotedecarinho.domain.model.Clothing

class GetPopularClothingUseCase(
    private val repository: Repository
){
    operator fun invoke(): Flow<PagingData<Clothing>>{
        return repository.getPopularClothing()
    }
}