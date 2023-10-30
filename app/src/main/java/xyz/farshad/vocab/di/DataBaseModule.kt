package xyz.farshad.vocab.di

import android.app.Application
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import xyz.farshad.vocab.VocabDatabase
import xyz.farshad.vocab.data.dao.CacheDao
import xyz.farshad.vocab.data.dao.ChapterDao
import xyz.farshad.vocab.data.dao.CourseDao
import xyz.farshad.vocab.data.dao.WordDao

val databaseModule = module {
    fun provideDatabase(application: Application): VocabDatabase =
        VocabDatabase.getDatabase(application)

    fun provideCourseDao(database: VocabDatabase): CourseDao = database.courseDao()
    fun provideChapterDao(database: VocabDatabase): ChapterDao = database.chapterDao()
    fun provideWordDao(database: VocabDatabase): WordDao = database.wordDao()
    fun provideCacheDao(database: VocabDatabase): CacheDao = database.cacheDao()

    single { provideDatabase(androidApplication()) }
    single { provideCourseDao(get()) }
    single { provideChapterDao(get()) }
    single { provideWordDao(get()) }
    single { provideCacheDao(get()) }
}
