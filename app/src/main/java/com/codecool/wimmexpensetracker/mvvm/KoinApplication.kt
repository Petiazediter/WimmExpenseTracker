package com.codecool.wimmexpensetracker.mvvm

import android.app.Application
import com.codecool.wimmexpensetracker.mvvm.repositories.AddCategoryActivityRepository
import com.codecool.wimmexpensetracker.mvvm.repositories.AddCategoryActivityRepositoryImplementation
import com.codecool.wimmexpensetracker.mvvm.view_models.AddCategoryActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    // Repos
    single<AddCategoryActivityRepository> {AddCategoryActivityRepositoryImplementation()}

    // ViewModels
    viewModel { AddCategoryActivityViewModel(get()) }
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