package com.fchazal.radiofrance.inject

import com.fchazal.radiofrance.brands.BrandsViewModel
import com.fchazal.radiofrance.shows.ShowsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<BrandsViewModel> {
        BrandsViewModel(
            useCase = get()
        )
    }
    viewModel<ShowsViewModel> {
        ShowsViewModel(
            useCase = get()
        )
    }
}