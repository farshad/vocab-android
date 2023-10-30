package xyz.farshad.vocab

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.farshad.vocab.data.dao.CacheDao
import xyz.farshad.vocab.data.dao.ChapterDao
import xyz.farshad.vocab.data.dao.CourseDao
import xyz.farshad.vocab.data.dao.WordDao
import xyz.farshad.vocab.data.entity.Cache
import xyz.farshad.vocab.data.entity.Chapter
import xyz.farshad.vocab.data.entity.Course
import xyz.farshad.vocab.data.entity.Word

@Database(entities = [Course::class, Chapter::class, Word::class, Cache::class], version = 8, exportSchema = false)
abstract class VocabDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao
    abstract fun chapterDao(): ChapterDao
    abstract fun wordDao(): WordDao
    abstract fun cacheDao(): CacheDao

    companion object {

        private var instance: VocabDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): VocabDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        VocabDatabase::class.java, "vocab_db")
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return instance as VocabDatabase
        }
    }
}
