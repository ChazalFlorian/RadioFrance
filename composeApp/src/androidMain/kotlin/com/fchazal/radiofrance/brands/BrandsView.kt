package com.fchazal.radiofrance.brands

import androidx.compose.runtime.*
import com.fchazal.radiofrance.brands.presentation.view.BrandsListView

@Composable
fun BrandsView(
    state: State<BrandsResultState>
) {
    when (val res = state.value) {
        is BrandsResultState.Loading -> {
            
        }
        is BrandsResultState.Error -> {

        }
        is BrandsResultState.Success -> {
            BrandsListView(res.brands)
        }
    }
}