package com.fchazal.radiofrance

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fchazal.radiofrance.brands.BrandsView
import com.fchazal.radiofrance.brands.BrandsViewModel
import com.fchazal.radiofrance.shows.ShowsView
import com.fchazal.radiofrance.shows.ShowsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.java.KoinJavaComponent.inject

@SuppressLint("RestrictedApi")
@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController(),
) {
    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = RadioFranceScreen.Brands.name,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(route = RadioFranceScreen.Brands.name) {
                val viewModel: BrandsViewModel by inject(BrandsViewModel::class.java)
                getBrands(viewModel = viewModel)
                val state = remember { mutableStateOf(viewModel.uiState) }
                BrandsView(
                    state = state.value.collectAsState(),
                    { id ->
                        navController.findDestination(RadioFranceScreen.Shows.name)?.id?.let {
                            navController.navigate(
                                it,
                                bundleOf("id" to id),
                                NavOptions.Builder()
                                    .setLaunchSingleTop(false)
                                    .setRestoreState(true)
                                    .build()
                            )
                        }
                    }
                )
            }
            composable(route = RadioFranceScreen.Shows.name) {
                val id = navController.currentBackStackEntry?.arguments?.getString("id")
                if (id.isNullOrBlank()) {
                    navController.popBackStack(RadioFranceScreen.Brands.name, false)
                } else {
                    val viewModel: ShowsViewModel by inject(ShowsViewModel::class.java)
                    getShows(viewModel, id)
                    val state = remember { mutableStateOf(viewModel.uiState) }
                    ShowsView(
                        state = state.value.collectAsState()
                    )
                }
            }
        }
    }
}

private fun getBrands(viewModel: BrandsViewModel) {
    CoroutineScope(Dispatchers.IO).launch {
        viewModel.getBrands()
    }
}

private fun getShows(viewModel: ShowsViewModel, id: String) {
    CoroutineScope(Dispatchers.IO).launch {
        viewModel.getShowsById(id)
    }
}

enum class RadioFranceScreen(val title: Int) {
    Brands(R.string.brands_screen_title),
    Shows(R.string.show_screen_title)
}