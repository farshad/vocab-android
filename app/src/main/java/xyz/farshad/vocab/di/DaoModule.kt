package xyz.farshad.vocab.di

import android.app.Application
import dagger.Module
import dagger.Provides
import xyz.farshad.vocab.data.dao.CourseDao
import xyz.farshad.vocab.data.dao.LevelDao
import xyz.farshad.vocab.data.dao.WordDao
import xyz.farshad.vocabToken.VocabDatabase
import javax.inject.Singleton

@Module
object DaoModule {

    @Singleton
    @Provides
    internal fun provideCourseDao(application: Application): CourseDao {
        val vocabDatabase = VocabDatabase.getInstance(application)
        return vocabDatabase.courseDao()
    }

    @Singleton
    @Provides
    internal fun provideLevelDao(application: Application): LevelDao {
        val vocabDatabase = VocabDatabase.getInstance(application)
        return vocabDatabase.levelDao()
    }

    @Singleton
    @Provides
    internal fun provideWordDao(application: Application): WordDao {
        val vocabDatabase = VocabDatabase.getInstance(application)
        return vocabDatabase.wordDao()
    }



}