package com.fchazal.radiofrance.brands

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fchazal.radiofrance.BrandsQuery
import com.fchazal.radiofrance.brands.domain.interactor.GetBrandsUseCase
import com.fchazal.radiofrance.brands.domain.model.BrandsResults
import com.fchazal.radiofrance.brands.presentation.BrandsUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class BrandsResultState {
    data object Loading: BrandsResultState()
    data class Success(val brands: List<BrandsUI>): BrandsResultState()

    data class Error(val error: String) : BrandsResultState()
}

class BrandsViewModel (
    private val useCase: GetBrandsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<BrandsResultState>(BrandsResultState.Loading)
    val uiState : StateFlow<BrandsResultState>
        get() = _uiState.asStateFlow()

    suspend fun getBrands() {
        viewModelScope.launch {
            when(val result = useCase.getBrands()) {
                is BrandsResults.Error -> {
                    BrandsResultState.Error(error = result.error)
                }
                is BrandsResults.Success -> {
                    BrandsResultState.Success(
                        brands = result.brands.toBrandsUI()
                    )
                }
            }
        }
    }
}

private fun List<BrandsQuery.Brand>.toBrandsUI(): List<BrandsUI> {
    return this.map {
        BrandsUI(
            id = it.id,
            title = it.title,
            description = it.description ?: ""
        )
    }
}
