package mxgenius.my.pacotedecarinho.domain.use_cases.get_selected_clothing

import mxgenius.my.pacotedecarinho.data.repository.Repository
import mxgenius.my.pacotedecarinho.domain.model.Clothing

class GetSelectedClothingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(clothingId: Int): Clothing {
        return repository.getSelectedClothing(clothingId = clothingId)
    }
}