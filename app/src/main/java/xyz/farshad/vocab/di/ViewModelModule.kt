package xyz.farshad.vocab.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.farshad.vocab.viewmodel.CourseViewModel
import xyz.farshad.vocab.viewmodel.ChapterViewModel
import xyz.farshad.vocab.viewmodel.SyncViewModel
import xyz.farshad.vocab.viewmodel.WordViewModel

val viewModelModule = module {
    viewModel { CourseViewModel(get()) }
    viewModel { ChapterViewModel(get()) }
    viewModel { WordViewModel(get()) }
    viewModel { SyncViewModel(get(), get(), get(), get()) }
}