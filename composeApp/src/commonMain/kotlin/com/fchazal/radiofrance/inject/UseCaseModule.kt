package com.fchazal.radiofrance.inject

import com.fchazal.radiofrance.brands.domain.interactor.GetBrandsUseCase
import com.fchazal.radiofrance.brands.domain.interactor.GetBrandsUseCaseImpl
import com.fchazal.radiofrance.shows.domain.interactor.GetShowByBrandIdUseCase
import com.fchazal.radiofrance.shows.domain.interactor.GetShowByBrandIdUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
        single<GetBrandsUseCase> {
                GetBrandsUseCaseImpl(
                    repository = get()
                )
        }
    single<GetShowByBrandIdUseCase> {
            GetShowByBrandIdUseCaseImpl(
                repository = get()
            )
    }
}