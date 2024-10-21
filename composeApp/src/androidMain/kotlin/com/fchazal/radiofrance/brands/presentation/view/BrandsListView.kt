package com.fchazal.radiofrance.brands.presentation.view

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.fchazal.radiofrance.brands.presentation.BrandsUI

@Composable
fun BrandsListView(brands: List<BrandsUI>) {
    LazyVerticalGrid(columns = GridCells.Fixed(1)){
        items(brands) {
            BrandsListItemView(it)
        }
    }
}