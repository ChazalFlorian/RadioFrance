package com.fchazal.radiofrance.shows.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fchazal.radiofrance.shows.presentation.model.ShowUI

@Composable
fun ShowListItemView(
    show: ShowUI,
    isEven: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isEven) Color.LightGray else Color.Gray
            )
    ) {
        Text(show.title)
    }
}