package com.fchazal.radiofrance.brands

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.fchazal.radiofrance.brands.presentation.view.BrandsListView

@Composable
fun BrandsView(
    viewModel: BrandsViewModel
) {
    val state = viewModel.uiState.collectAsState().value

    when (state) {
        is BrandsResultState.Loading -> {
            
        }
        is BrandsResultState.Error -> {

        }
        is BrandsResultState.Success -> {
            BrandsListView(state.brands)
        }
    }
}