package com.fchazal.radiofrance.brands

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.fchazal.radiofrance.brands.presentation.view.BrandsListView

@Composable
fun BrandsView(
    state: State<BrandsResultState>,
    onClick: (String) -> Unit,
) {
    when (val res = state.value) {
        is BrandsResultState.Loading -> {
        }

        is BrandsResultState.Error -> {
        }

        is BrandsResultState.Success -> {
            BrandsListView(res.brands, onClick)
        }
    }
}