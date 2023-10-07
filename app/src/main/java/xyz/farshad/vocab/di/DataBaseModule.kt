package xyz.farshad.vocab.di

import android.app.Application
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import xyz.farshad.vocab.VocabDatabase
import xyz.farshad.vocab.data.dao.CourseDao
import xyz.farshad.vocab.data.dao.ChapterDao
import xyz.farshad.vocab.data.dao.WordDao

val databaseModule = module {
    fun provideDatabase(application: Application): VocabDatabase =
        VocabDatabase.getDatabase(application)

    fun provideTokenDao(database: VocabDatabase): CourseDao = database.courseDao()
    fun provideTagDao(database: VocabDatabase): ChapterDao = database.chapterDao()
    fun provideRepeatDao(database: VocabDatabase): WordDao = database.wordDao()

    single { provideDatabase(androidApplication()) }
    single { provideTokenDao(get()) }
    single { provideTagDao(get()) }
    single { provideRepeatDao(get()) }
}
