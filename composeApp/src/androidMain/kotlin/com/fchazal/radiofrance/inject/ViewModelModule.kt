package com.fchazal.radiofrance.inject

import com.fchazal.radiofrance.brands.BrandsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<BrandsViewModel> {
        BrandsViewModel(
            useCase = get()
        )
    }
}