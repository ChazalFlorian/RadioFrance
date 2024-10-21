package com.fchazal.radiofrance.inject

import com.fchazal.radiofrance.brands.data.repository.BrandsRepositoryImpl
import com.fchazal.radiofrance.brands.domain.repository.BrandsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BrandsRepository> {
        BrandsRepositoryImpl()
    }
}