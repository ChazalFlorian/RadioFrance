package com.fchazal.radiofrance

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fchazal.radiofrance.brands.BrandsView
import com.fchazal.radiofrance.brands.BrandsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.java.KoinJavaComponent.inject

@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController()
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
                BrandsView(
                    viewModel = viewModel
                )
            }
        }
    }
}

private fun getBrands(viewModel: BrandsViewModel) {
    CoroutineScope(Dispatchers.IO).launch {
        viewModel.getBrands()
    }
}

enum class RadioFranceScreen(val title: Int) {
    Brands(R.string.brands_screen_title)
}