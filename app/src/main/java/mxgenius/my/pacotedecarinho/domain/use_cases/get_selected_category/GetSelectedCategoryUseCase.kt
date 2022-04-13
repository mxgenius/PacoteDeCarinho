package mxgenius.my.pacotedecarinho.domain.use_cases.get_selected_category

import mxgenius.my.pacotedecarinho.data.repository.Repository
import mxgenius.my.pacotedecarinho.domain.model.Clothing

class GetSelectedCategoryUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(category: String) : Clothing {
        return repository.getSelectedCategory(category = category)
    }
}