package xyz.farshad.vocab

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.farshad.vocab.data.dao.CourseDao
import xyz.farshad.vocab.data.dao.LevelDao
import xyz.farshad.vocab.data.dao.WordDao
import xyz.farshad.vocab.data.model.Course
import xyz.farshad.vocab.data.model.Level
import xyz.farshad.vocab.data.model.Word

@Database(entities = [Course::class, Level::class, Word::class], version = 1, exportSchema = false)
abstract class VocabDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao
    abstract fun levelDao(): LevelDao
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
