package xyz.farshad.vocab

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.farshad.vocab.data.dao.CourseDao
import xyz.farshad.vocab.data.dao.ChapterDao
import xyz.farshad.vocab.data.dao.WordDao
import xyz.farshad.vocab.data.model.Course
import xyz.farshad.vocab.data.model.Chapter
import xyz.farshad.vocab.data.model.Word

@Database(entities = [Course::class, Chapter::class, Word::class], version = 2, exportSchema = false)
abstract class VocabDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao
    abstract fun levelDao(): ChapterDao
    abstract fun wordDao(): WordDao

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
