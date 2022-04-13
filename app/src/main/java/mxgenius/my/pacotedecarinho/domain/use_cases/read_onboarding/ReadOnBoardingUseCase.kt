package mxgenius.my.pacotedecarinho.domain.use_cases.read_onboarding

import kotlinx.coroutines.flow.Flow
import mxgenius.my.pacotedecarinho.data.repository.Repository

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}