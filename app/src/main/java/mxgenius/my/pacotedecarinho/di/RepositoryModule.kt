package mxgenius.my.pacotedecarinho.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mxgenius.my.pacotedecarinho.data.repository.DataStoreOperationsImpl
import mxgenius.my.pacotedecarinho.data.repository.Repository
import mxgenius.my.pacotedecarinho.domain.repository.DataStoreOperations
import mxgenius.my.pacotedecarinho.domain.use_cases.UseCases
import mxgenius.my.pacotedecarinho.domain.use_cases.get_all_clothing.GetAllClothingUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.get_popular_clothing.GetPopularClothingUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.get_selected_category.GetSelectedCategoryUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.get_selected_clothing.GetSelectedClothingUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.search_clothing.SearchClothingUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllClothingUseCase = GetAllClothingUseCase(repository),
            getPopularClothingUseCase = GetPopularClothingUseCase(repository),
            searchClothingUseCase = SearchClothingUseCase(repository),
            getSelectedCategoryUseCase = GetSelectedCategoryUseCase(repository),
            getSelectedClothingUseCase = GetSelectedClothingUseCase(repository),
        )
    }
}