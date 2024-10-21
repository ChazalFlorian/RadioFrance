package com.fchazal.radiofrance.brands.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.fchazal.radiofrance.brands.presentation.BrandsUI

@Composable
fun BrandsListItemView(brand: BrandsUI) {
    Row {
        Column {

        }
        Column {
            Text(brand.title)
            Text(brand.description)
        }
    }
}