package com.codecool.wimmexpensetracker.mvvm

import android.app.Application
import com.codecool.wimmexpensetracker.mvvm.repositories.*
import com.codecool.wimmexpensetracker.mvvm.view_models.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

    viewModel { AddCategoryActivityViewModel(get()) }
    single<AddCategoryActivityRepository> {AddCategoryActivityRepositoryImplementation()}

    viewModel { CategoriesViewModel(get()) }
    single<CategoriesRepository>{CategoriesRepositoryImp()}

    viewModel { ExpenseFragmentViewModel(get()) }
    single<ExpenseFragmentRepository>{ExpenseFragmentRepositoryImp()}

    viewModel { HomeFragmentViewModel(get()) }
    single<HomeFragmentRepository>{HomeFragmentRepositoryImp()}

    viewModel { NewExpenseViewModel(get())}
    single<NewExpenseRepository>{NewExpenseRepositoryImpl()}

    viewModel { RecyclerAdapterViewModel(get())}
    single<RecyclerAdapterRepository>{RecyclerAdapterRepositoryImp()}
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