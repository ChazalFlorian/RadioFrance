package com.fchazal.radiofrance.inject

import com.fchazal.radiofrance.brands.data.repository.BrandsRepositoryImpl
import com.fchazal.radiofrance.brands.domain.repository.BrandsRepository
import com.fchazal.radiofrance.shows.data.repository.ShowsRepositoryImpl
import com.fchazal.radiofrance.shows.domain.repository.ShowsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BrandsRepository> {
        BrandsRepositoryImpl()
    }
    single<ShowsRepository> {
        ShowsRepositoryImpl()
    }
}