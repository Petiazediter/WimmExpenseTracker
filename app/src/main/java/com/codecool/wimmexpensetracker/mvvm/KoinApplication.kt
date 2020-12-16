package com.codecool.wimmexpensetracker.mvvm

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    //single<
}

class KoinApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@KoinApplication)
            modules(appModule)
        }
    }
}