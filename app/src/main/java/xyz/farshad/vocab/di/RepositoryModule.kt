package xyz.farshad.vocab.di

import org.koin.dsl.module
import xyz.farshad.vocab.data.repository.ChapterRepository
import xyz.farshad.vocab.data.repository.CourseRepository
import xyz.farshad.vocab.data.repository.SyncRepository
import xyz.farshad.vocab.data.repository.WordRepository

val repositoryModule = module {
    single { CourseRepository(get(), get()) }
    single { ChapterRepository(get()) }
    single { WordRepository(get()) }
    single { SyncRepository(get()) }
}