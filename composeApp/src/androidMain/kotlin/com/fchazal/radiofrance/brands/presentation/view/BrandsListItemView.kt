package com.fchazal.radiofrance.brands.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fchazal.radiofrance.brands.presentation.model.BrandsUI

@Composable
fun BrandsListItemView(
    brand: BrandsUI,
    isEven: Boolean,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.background(
            if(isEven) Color.LightGray else Color.Gray
        ).clickable {
            onClick(brand.id)
        }
    ) {
        Column {
            Text(brand.title)
            Text(brand.baseline)
            Text(brand.description)
        }
    }
}
