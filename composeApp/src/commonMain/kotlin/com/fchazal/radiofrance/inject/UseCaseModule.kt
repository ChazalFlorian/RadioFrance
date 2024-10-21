package com.fchazal.radiofrance.inject

import com.fchazal.radiofrance.brands.domain.interactor.GetBrandsUseCase
import com.fchazal.radiofrance.brands.domain.interactor.GetBrandsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
        single<GetBrandsUseCase> {
                GetBrandsUseCaseImpl(
                    repository = get()
                )
        }
}