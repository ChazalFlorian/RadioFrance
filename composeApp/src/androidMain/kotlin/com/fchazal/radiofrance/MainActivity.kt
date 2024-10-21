package com.fchazal.radiofrance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fchazal.radiofrance.brands.presentation.BrandsViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val brandsViewModel: BrandsViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}