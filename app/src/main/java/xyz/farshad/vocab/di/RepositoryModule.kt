package xyz.farshad.vocab.di

import org.koin.dsl.module
import xyz.farshad.vocab.data.repository.*

val repositoryModule = module {
    single { CourseRepository(get(), get()) }
    single { SelectionRepository(get()) }
    single { ChapterRepository(get()) }
    single { WordRepository(get()) }
    single { SyncRepository(get()) }
    single { CacheRepository(get()) }
    single { AuthRepository(get()) }
}