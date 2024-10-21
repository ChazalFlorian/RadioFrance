package com.fchazal.radiofrance.brands.presentation.view

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import com.fchazal.radiofrance.brands.presentation.model.BrandsUI

@Composable
fun BrandsListView(
    brands: List<BrandsUI>,
    onClick: (String) -> Unit
    ) {
    LazyVerticalGrid(columns = GridCells.Fixed(1)){
        itemsIndexed(brands) { index, brand ->
            BrandsListItemView(brand, (index % 2 == 0), onClick)
        }
    }
}