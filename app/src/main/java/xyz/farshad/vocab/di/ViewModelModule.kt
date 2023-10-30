package xyz.farshad.vocab.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.farshad.vocab.viewmodel.*

val viewModelModule = module {
    viewModel { CourseViewModel(get(), get(), get(), get()) }
    viewModel { ChapterViewModel(get()) }
    viewModel { WordViewModel(get()) }
    viewModel { CacheViewModel(get()) }
    viewModel { AuthViewModel(get()) }
    viewModel { SyncViewModel(get(), get(), get(), get()) }
}