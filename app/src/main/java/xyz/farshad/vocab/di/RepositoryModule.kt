package xyz.farshad.vocab.di

import org.koin.dsl.module
import xyz.farshad.vocab.data.repository.CourseRepository
import xyz.farshad.vocab.data.repository.LevelRepository
import xyz.farshad.vocab.data.repository.SyncRepository
import xyz.farshad.vocab.data.repository.WordRepository

val repositoryModule = module {
    single { CourseRepository(get()) }
    single { LevelRepository(get()) }
    single { WordRepository(get()) }
    single { SyncRepository(get()) }
}