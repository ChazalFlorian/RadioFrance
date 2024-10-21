package com.fchazal.radiofrance.brands

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.fchazal.radiofrance.brands.presentation.BrandsResultState
import com.fchazal.radiofrance.brands.presentation.BrandsViewModel
import kotlinx.coroutines.flow.collect

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

        }
        else -> {}
    }
}