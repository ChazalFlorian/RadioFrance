package com.fchazal.radiofrance.shows

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.fchazal.radiofrance.shows.presentation.view.ShowsListView

@Composable
fun ShowsView(
    state: State<ShowsResultState>,
) {
    when (val res = state.value) {
        is ShowsResultState.Error -> {
        }

        ShowsResultState.Loading -> {
        }

        is ShowsResultState.Success -> {
            ShowsListView(res.shows)
        }
    }
}