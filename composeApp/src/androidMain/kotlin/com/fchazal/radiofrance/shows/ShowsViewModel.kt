package com.fchazal.radiofrance.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fchazal.radiofrance.ShowsQuery
import com.fchazal.radiofrance.shows.domain.interactor.GetShowByBrandIdUseCase
import com.fchazal.radiofrance.shows.domain.model.ShowsResults
import com.fchazal.radiofrance.shows.presentation.model.ShowUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ShowsResultState {
    data object Loading : ShowsResultState()
    data class Success(val shows: List<ShowUI>) : ShowsResultState()
    data class Error(val error: String) : ShowsResultState()
}

class ShowsViewModel(
    private val useCase: GetShowByBrandIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ShowsResultState>(ShowsResultState.Loading)
    val uiState: StateFlow<ShowsResultState>
        get() = _uiState

    fun getShowsById(id: String) {
        viewModelScope.launch {
            when (val shows = useCase.getShows(id)) {
                is ShowsResults.Error -> {
                    _uiState.emit(ShowsResultState.Error(error = shows.error))
                }

                is ShowsResults.Success -> {
                    _uiState.emit(
                        ShowsResultState.Success(
                            shows.shows.toShowUI()
                        )
                    )
                }
            }
        }
    }

    private fun List<ShowsQuery.Node>.toShowUI(): List<ShowUI> {
        return this.map {
            ShowUI(
                title = it.title
            )
        }
    }
}