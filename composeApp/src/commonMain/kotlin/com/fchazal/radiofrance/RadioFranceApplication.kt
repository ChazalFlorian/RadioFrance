package com.fchazal.radiofrance

import android.app.Application
import com.fchazal.radiofrance.inject.applicationModule
import com.fchazal.radiofrance.inject.repositoryModule
import com.fchazal.radiofrance.inject.useCaseModule
import com.fchazal.radiofrance.inject.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RadioFranceApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RadioFranceApplication)
            modules(
                applicationModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}