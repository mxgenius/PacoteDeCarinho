package mxgenius.my.pacotedecarinho.domain.use_cases

import mxgenius.my.pacotedecarinho.domain.use_cases.get_all_clothing.GetAllClothingUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.get_popular_clothing.GetPopularClothingUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.get_selected_category.GetSelectedCategoryUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.get_selected_clothing.GetSelectedClothingUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import mxgenius.my.pacotedecarinho.domain.use_cases.search_clothing.SearchClothingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllClothingUseCase: GetAllClothingUseCase,
    val getPopularClothingUseCase: GetPopularClothingUseCase,
    val searchClothingUseCase: SearchClothingUseCase,
    val getSelectedCategoryUseCase: GetSelectedCategoryUseCase,
    val getSelectedClothingUseCase: GetSelectedClothingUseCase,
)