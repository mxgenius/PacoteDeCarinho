package mxgenius.my.pacotedecarinho.domain.use_cases.save_onboarding

import mxgenius.my.pacotedecarinho.data.repository.Repository

class SaveOnBoardingUseCase(private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}