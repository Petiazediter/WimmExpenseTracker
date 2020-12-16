package com.codecool.wimmexpensetracker.mvvm

import android.app.Application
import com.codecool.wimmexpensetracker.mvvm.repositories.*
import com.codecool.wimmexpensetracker.mvvm.view_models.AddCategoryActivityViewModel
import com.codecool.wimmexpensetracker.mvvm.view_models.CategoriesViewModel
import com.codecool.wimmexpensetracker.mvvm.view_models.ExpenseFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    // Repos
    single<AddCategoryActivityRepository> {AddCategoryActivityRepositoryImplementation()}
    single<CategoriesRepository>{CategoriesRepositoryImp()}
    single<ExpenseFragmentRepository>{ExpenseFragmentRepositoryImp()}

    // ViewModels
    viewModel { AddCategoryActivityViewModel(get()) }
    viewModel { CategoriesViewModel(get()) }
    viewModel { ExpenseFragmentViewModel(get()) }
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